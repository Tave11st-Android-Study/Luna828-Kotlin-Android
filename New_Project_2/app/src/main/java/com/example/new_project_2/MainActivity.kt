package com.example.new_project_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<stopwatchViewModel>()

            val sec = viewModel.sec.value
            val mili = viewModel.mili.value
            val isRunning = viewModel.isRunning.value
            val lapTimes = viewModel.laptimes.value

            HomeScreen(
                sec = sec,
                miliSec = mili,
                isRunning = isRunning,
                lapTimes = lapTimes,
                onReset = {
                    println("리셋")
                    viewModel.reset() },
                onToggle = { running ->
                    println("onToggle 눌림")
                    if(running){
                        viewModel.pause()
                    } else {
                        viewModel.start()
                    }
                },
                onLapTime = {
                    println("lap 눌림")
                    viewModel.recordLapTime()}
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    sec: Int,
    miliSec: Int,
    isRunning: Boolean,
    lapTimes: List<String>,
    onReset: () -> Unit,
    onToggle: (Boolean) -> Unit,//현재 상태 알리기
    onLapTime: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                { Text(text = "스톱워치") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ){
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$miliSec")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
            ) {
                lapTimes.forEach{
                    lapTime ->
                    Text(lapTime)
                }
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                FloatingActionButton(
                    onClick = { onReset() },
                    backgroundColor = Color.Red,
                ) {
                    Image(painter = painterResource(id = R.drawable.baseline_refresh_24), contentDescription = "refresh")
                }
                FloatingActionButton(
                    onClick = { onToggle(isRunning) },
                    backgroundColor = Color.Green,
                ) {
                    Image(
                        painter = painterResource(
                            id =
                            if (isRunning) R.drawable.baseline_pause_24
                            else R.drawable.baseline_play_arrow_24
                        ),
                        contentDescription = "start/pause"
                    )
                }
                Button(onClick = { onLapTime() }) {
                    Text(text = "랩타임")
                }
            }
        }
    }
}