package com.example.codelabstudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.codelabstudy.ui.theme.CodeLabStudyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            CodeLabStudyTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = MaterialTheme.colors.primary,
                ) {
                    Scaffold(
                        topBar = { AppBar() }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "HI, Android",
                                modifier = Modifier.padding(16.dp, vertical = 8.dp)
                            )
                            FilledTextField(colors = Color.White, shapes = MaterialTheme.shapes.small)
                            LoginButton(onClick = {}, modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(){
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ){}
}

@Composable
fun FilledTextField(
    shapes: Shape = MaterialTheme.shapes.small.copy(
        bottomStart = ZeroCornerSize,
        bottomEnd = ZeroCornerSize,
    ),
    colors: Color
){
    Text("HI")
}

@Composable
fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)) {
        ProvideTextStyle(value = TextStyle(Color.LightGray)) {
            Text(text = "로그인")
        }
    }
}