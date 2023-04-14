package com.example.stopwatch_hw

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stopwatch_hw.ui.theme.StopWatch_HWTheme
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopWatch_HWTheme(true) {
                //Stop Watch 만들기 과제 (Coroutine or Thread) 만들어서 진행
                MainPage(this)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(context: Context) {
    var time = 0
    var timerTask: Timer? = null
    var isRunning = false

    val min = remember {
        mutableStateOf(0)
    }
    val sec = remember {
        mutableStateOf(0)
    }
    val mili = remember {
        mutableStateOf(0)
    }

    fun pause() {
        isRunning = false
        timerTask?.cancel()
    }

    fun start() {
        isRunning = true
        timerTask = timer(period = 10) {
            time++
            val tmpTime = time / 100
            min.value = tmpTime / 60
            sec.value = tmpTime - (min.value * 60)
            mili.value = time % 100
        }
    }

    fun reset() {
        timerTask?.cancel()
        time = 0
        isRunning = false
        min.value = 0 //초기화 시켜주기
        sec.value = 0
        mili.value = 0
    }

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TimerTextView(min = min.value, sec = sec.value, mili = mili.value)
            TimerButtonView(
                isRunning = isRunning,
                onReset = { reset() },
                onPlayPause = { running ->
                    CoroutineScope(Dispatchers.Main).launch {
                        if (running) {
                            pause()
                        } else {
                            start()
                            //ㅇ ㅏ.. 
                            if (min.value == 0 && sec.value == 3 && mili.value == 0) {
                                pause()
                                Toast.makeText(
                                    context,
                                    "실행을 자동으로 중지합니다",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                }
            )
        }
    }
}

@Composable
fun TimerTextView(
    min: Int,
    sec: Int,
    mili: Int,
) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = if (min < 10) "0$min" else "$min",
            fontSize = 100.sp
        )
        Text(
            text = if (sec < 10) ":0$sec" else ":$sec",
            fontSize = 100.sp
        )
        Text(
            text = if (mili < 10) ".0$mili" else ".$mili",
            fontSize = 50.sp
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun TimerButtonView(
    isRunning: Boolean,
    onReset: () -> Unit,
    onPlayPause: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .size(200.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FloatingActionButton(
            onClick = onReset,
            backgroundColor = Color.Red
        )
        {
            Image(
                painter = painterResource(id = R.drawable.baseline_square_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
        FloatingActionButton(
            onClick = { onPlayPause(isRunning) },
            backgroundColor = Color.Green
        ) {
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

