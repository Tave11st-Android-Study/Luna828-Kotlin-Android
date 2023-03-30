package com.example.stopwatch

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stopwatch.ui.theme.StopWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    sec: Int,
    milli: Int,
    isRunning: Boolean,
    lapTimes: List<String>,
    onReset: () -> Unit,
    onToggle: (Boolean) -> Boolean, //현재상태 알리기
    onLapTime: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "StopWatch")})
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "$sec", fontSize = 100.sp)
                Text(text = "$milli")
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f) //1f 나머지 영역 다 차지
                    .verticalScroll(rememberScrollState())
            ) {
                lapTimes.forEach{
                    lapTime -> Text(lapTime)
                }
            }
            
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FloatingActionButton(
                    onClick = { onReset },
                    backgroundColor = Color.Red,
                ) {
                    Image(painter = painterResource(id = R.drawable.baseline_refresh_24), contentDescription = "refresh")
                }
                FloatingActionButton(
                    onClick = { onToggle(isRunning) },
                    backgroundColor = Color.Green,
                ) {
                    Image(painter = painterResource(
                        id =
                        if(isRunning) R.drawable.baseline_pause_24
                        else R.drawable.baseline_play_arrow_24),
                        contentDescription = "start/pause"
                    )
                }
                Button(onClick = {onLapTime}) {
                    Text(text = "랩타임")
                }
            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}