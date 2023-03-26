package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Luna")
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
       topBar = { SmallTopAppBar(title = { Text(text = name)})},
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
        Modifier.padding(80.dp).background(Color(0xffeaffd0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Luna", Modifier.padding(all = 10.dp).background(Color.Yellow))
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
        Greeting("Luna")
    }
}