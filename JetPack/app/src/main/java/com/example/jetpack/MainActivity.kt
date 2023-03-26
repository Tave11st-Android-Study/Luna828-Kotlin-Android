package com.example.jetpack

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(name = "Luna")
                }
            }
        }
    }
}

//View를 뜻함
@Composable
fun Greeting(name: String) {
   Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(
       start = 20.dp,
       top = 12.dp,
       end = 20.dp,
       bottom = 12.dp
   )) {
       Text(text = "Like $name!")
       Icon(Icons.Filled.Favorite, contentDescription = "Favorite", )
   }
}

//split 미리 보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackTheme {
        Greeting("Android")
    }
}