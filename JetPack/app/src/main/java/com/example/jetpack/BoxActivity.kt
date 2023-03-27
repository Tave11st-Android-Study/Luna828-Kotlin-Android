package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

class ComposableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { //setContent 이 영역은 @Composable들만 들어올 수있는 영역임
            //Box 같은 경우는 겹치는 것이 허용되는 Frame Layout 같은 것
            Box {
                Text("Hello")
            }
        }
    }
}

@Composable //컴포저블이 붙어져 있는 함수는 함수 이름을 대문자로 시작 하게 만든다.
fun BoxPractice(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Box ( //Box 컴포져블
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Text("Hello")
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        )
        {
            Text("hello")
        }
    }
}