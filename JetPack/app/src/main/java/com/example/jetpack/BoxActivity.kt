package com.example.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

class BoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { //setContent 이 영역은 @Composable들만 들어올 수있는 영역임
            //Box 같은 경우는 겹치는 것이 허용되는 Frame Layout 같은 것
            val scrollState = rememberScrollState()

            Box ( //Box 컴포져블
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
            ){
                Box(modifier = Modifier
                    .background(Color.Gray),
                    contentAlignment = Alignment.Center,
                ) {
                    ListPractice()
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd
                )
                {
                    Text("hello")
                }
            }
        }
    }
}

@Composable //컴포저블이 붙어져 있는 함수는 함수 이름을 대문자로 시작 하게 만든다.
fun BoxPractice(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ListPractice() {
//    Column(
//        modifier = Modifier.background(Color.Gray),
//    ) {
//        for (i in 1..50){
//            Text(text = "글씨 $i")
//        }
//    }
    LazyColumn( //LazyColumn 은 for문으로 반복문을 사용할 수 없음
        modifier = Modifier.background(Color.Gray),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Header")
        }
        items(50) {index ->
            Text("글씨 $index")
        }
        item {
            Text("Footer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {

    val scrollState = rememberScrollState()

    Box ( //Box 컴포져블
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ){
        Box(modifier = Modifier
            .background(Color.Gray),
            contentAlignment = Alignment.Center
            ) {
            ListPractice()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        )
        {
            Text("hello")
        }
    }
}