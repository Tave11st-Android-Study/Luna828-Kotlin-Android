package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme
import androidx.compose.foundation.Image as Image

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable { //by 위임 기능..? , by 쓰는 방법 import androidx.compose.runtime.getValue / setValue를 직접 import해주면 가능
                mutableStateOf(false) //보존이 필요한 부분에서는 rememberSaveable사용하면됨
            }
            ImageCard(
                isFavorite = isFavorite,
                modifier = Modifier.fillMaxWidth(0.5f).padding(16.dp), //비율
            ){
                favorite ->
                isFavorite = favorite
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean, //변수가 아니라 상수이기 때문에 callback 함수를 만들어줘야함 
    onTabFavorite: (Boolean) -> Unit, //callback , Unit return이 없는 형태
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier =  Modifier.height(200.dp),

        ){
            Image(
                painter = painterResource(id = R.drawable.painter),
                contentDescription = "painter",
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ){
                IconButton(onClick = {
                    onTabFavorite(!isFavorite)
                }) {
                    Icon(
                        imageVector = if(isFavorite)Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

//View를 뜻함
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {

   Scaffold(
       topBar = { SmallTopAppBar(
           title = {
               Text(text = name)
           })
                },
       floatingActionButtonPosition = FabPosition.End,
       floatingActionButton = {
           FloatingActionButton(onClick = { /*TODO*/ }) {
               Text(text = "Click")
           }
       }
   ) {
      MyComposableView()

   }
}

@Composable
fun MyComposableView() {
    Log.d("TAG", "MyComposableView: ")
    // horizental LinearView
    Row(
        Modifier
            .padding(80.dp)
            .background(Color(0xffeaffd0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Luna",
            Modifier
                .padding(all = 10.dp)
                .background(Color.Yellow))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "1234-1234")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "1998-08-28")
    }
}

//split 미리 보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackTheme {
        Column() {
            Greeting("Luna")
            Column() {
                Text("Hello", Modifier.padding(30.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Text("Luna")
            }
        }
    }
}