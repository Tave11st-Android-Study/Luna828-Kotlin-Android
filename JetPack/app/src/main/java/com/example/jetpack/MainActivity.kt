package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.ui.theme.ui.theme.JetPackTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //State 심화
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()){
    var text1 = remember {
        mutableStateOf("Hello World")
    }
    
    var text2 by remember {
        mutableStateOf("Hello World")
    }

    var (text: String, setText: (String) -> Unit) = remember{
        mutableStateOf("Hello World")
    }

    Column(){
        Text("Hello World")
        Button(onClick = {
            text1.value = "변경"
            print(text1.value)
            text2 = "변경"
            print(text2)
            setText("변경")
            print(setText)
            viewModel.changeValue("변경")
        }) {
            Text("클릭")
        }
        TextField(value = text, onValueChange = setText)
    }

}


//split 미리 보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}