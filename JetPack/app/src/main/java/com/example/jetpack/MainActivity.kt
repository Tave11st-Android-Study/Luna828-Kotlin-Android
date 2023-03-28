package com.example.jetpack

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //State 심화
            HomeScreen()
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "first",
            ){
                composable("first") {
                    FirstScreen(navController = navController)
                }
                composable("second") {
                    SecondScreen(navController = navController)
                }
                composable("third/{value}") { backStackEntry ->
                    ThirdScreen(navController = navController, value = backStackEntry.arguments?.getString("value") ?: "")
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
    val (value, setValue) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("second")
            }
        ) {
            Text(text = "두번째!")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(
            onClick = {
                if(value.isNotEmpty()){
                    navController.navigate("third/$value")
                }
            }
        ) {
            Text(text = "세번째!")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "두번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text(text = "뒤로 가기")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController, value: String){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "세번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(value)
        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text(text = "뒤로 가기")
        }
    }
}

//View를 뜻함
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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