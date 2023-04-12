package com.example.mygallery

import android.Manifest
import android.app.Application
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var granted by remember { mutableStateOf(false) }
            //권한요청
            val launcher =
            // rememberLauncherForActivityResult 사용자 인터랙션에 대한 결과를 처리하기 위해 제공되는 API 중 하나
                //ActivityResultContracts.RequestPermission() -> 권한 요청 결과를 처리하기 위한 Contract입니다. Contract는 ActivityResult API와 함께 사용됩니다. 이 Contract를 사용하면 권한 요청 시 사용자의 동의 여부에 대한 결과를 처리할 수 있습니다.
                rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    granted = isGranted
                }

            //권한이 있는지 체크 코드
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE, //사진을 가지고 오는 것..
                ) == PackageManager.PERMISSION_GRANTED //권한이 수락되었는지를 묻는 것
            ) {
                granted = true
            }
            if (granted) {
                Text(text = "권한 허용 됨")
            } else {
                PermissionRequestScreen {
                    launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted -> }는
                    // 권한 요청 Contract를 사용하여 권한 요청 결과를 처리하는 람다식을 가진 ActivityResultLauncher 객체를 생성합니다.
                    // 이 객체는 launch() 메서드를 호출하여 권한 요청을 시작할 수 있습니다. launch() 메서드에서는 사용자의 권한 동의 여부를 나타내는 isGranted 변수가 콜백 함수의 매개변수로 전달됩니다.
                }
            }
        }
    }
}

//ViewModel 만들기
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _PhotoUris = mutableStateOf(emptyList<Uri>())
    val PhotoUris: State<List<Uri>> = _PhotoUris //사진이 여러장이니까 List로 만들어준다.

    fun fetchPhotos() {
        val uris = mutableListOf<Uri>()

        //사진을 가져올거임!
        // 사진을 가져올 때, contentProvider로 사진을 얻을 수 있음 -> 그냥 ViewModel()는 contentProvider에서 Context를 얻을 수 없기에 .. AndroidViewModel로 변경
        getApplication<Application>().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            //조건
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC" //DESC 내림차순 DATE_TAKEN 찍은순서대로
        )?.use {
            //자동으로 close가 됨 cursor는 close를 해줘야함
                cursor -> //여러가지 데이터를 가지고있는 상태
            val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID) //ID가 몇번째 인덱스에 있는지

            while (cursor.moveToNext()) { //순환
                val id = cursor.getLong(idIndex)

                //uri 변환
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id,
                ) //Uri를 알고있을 때 , id를 만들기 편리

                uris.add(contentUri)
            }
        }

        _PhotoUris.value = uris
    }
}


@Composable
fun PermissionRequestScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("권한이 허용되지 않았습니다")
        Button(onClick = onClick) {
            Text("권한 요청")
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(photoUris: List<Uri>) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            //현재 위치를 기억하기위한 상태
            state = pagerState,
            count = photoUris.size,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxSize(),
        ) { pageIndex ->
            Card() {
                Image(
                    painter = rememberImagePainter(
                        //coil에서 제공함
                        data = photoUris[pageIndex],
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop, //전체적으로 이미지가 꽉차 보이게 하는 거..
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}