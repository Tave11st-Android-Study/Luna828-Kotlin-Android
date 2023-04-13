package com.example.stopwatch_hw

import android.annotation.SuppressLint
import android.content.ServiceConnection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatch_hw.ui.theme.StopWatch_HWTheme
import kotlinx.coroutines.*
import java.nio.file.WatchService

class MainActivity : ComponentActivity() {
    /*
    * 1. 초기 상태 time = 0
    * 2. 필요한 함수 : 시작, 정지, 리셋
    * 3. 조건: 버튼 클릭시 시작버튼과 일시정지 버튼 바꾸게하기, 시간 00:00:00 만약 99:99:99가되면 실행정지를 알리는 Toast띄우기
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopWatch_HWTheme(true) {
                //Stop Watch 만들기 과제 (Coroutine or Thread) 만들어서 진행
                MainPage()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage() {
    val context = LocalContext.current
    val viewModel = viewModel<MainViewModel>()
    val min = viewModel.min.value
    val sec = viewModel.sec.value
    val mili = viewModel.mili.value
    val isRunning = viewModel.isRunning.value
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = "$min:$sec.$mili", fontSize = 100.sp) //분
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .size(200.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FloatingActionButton(
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.reset()
                        }
                    },
                    backgroundColor = Color.Red
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_square_24),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }
                FloatingActionButton(onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (isRunning) {
                            viewModel.pause()
                        } else {
                            viewModel.start(context)
                        }
                    }
                }, backgroundColor = Color.Green) {
                    Image(
                        painter = painterResource(
                            id =
                            if (isRunning) R.drawable.baseline_pause_24
                            else R.drawable.baseline_play_arrow_24
                        ),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }

            }
        }
    }
}
