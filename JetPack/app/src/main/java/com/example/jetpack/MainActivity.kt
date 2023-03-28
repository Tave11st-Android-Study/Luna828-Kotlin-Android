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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    private val viewModel by viewModels<MainViewModel>() //viewModel 사용법

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    viewModel.data.value,
                    fontSize = 30.sp
                )
                Button(onClick = {
                    viewModel.changeValue()
                }) {
                    Text("변경")
                }
            }
        }
    }
}

class MainViewModel : ViewModel(){
    //ViewModel 같은 경우는 액티비티와 라이프 사이클을 동일하게 가져가는 특성 remember에 신경쓰지 않아도됨 (remember가 없어도 데이터가 유지가 되는 이유는 VM 자체가 액티비티하고 라이프 사이클을 같이 가져가서!)
    private val _data = mutableStateOf("Hello")
    val data: State<String> = _data //읽기 전용

    fun changeValue() {
        _data.value = "World"
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ViewModel(){
    val data = remember {
        mutableStateOf("Hello")
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            data.value,
            fontSize = 30.sp
        )
        Button(onClick = {
            data.value = "World"
        }) {
            Text("변경")
        }
    }
}

@Composable
fun Navigation(){
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
        composable("third/{value}") { backStackEntry -> //backStackEntry를 통해 {value}값을 얻을 수 있음
            ThirdScreen(navController = navController, value = backStackEntry.arguments?.getString("value") ?: "")
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {

   Scaffold(
       topBar = { SmallTopAppBar(
           title = {
               Text(text = name)
           })
                },
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
        Modifier
            .padding(80.dp)
            .background(Color(0xffeaffd0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Luna",
            Modifier
                .padding(all = 10.dp)
                .background(Color.Yellow))
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
        Column() {
            Greeting("Luna")
            Column() {
                Text("Hello", Modifier.padding(30.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Text("Luna")
            }
        }
    }
}