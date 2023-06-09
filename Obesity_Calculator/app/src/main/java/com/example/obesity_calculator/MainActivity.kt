package com.example.obesity_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<BmiViewModel2>()
            val navController = rememberNavController()

            val bmi = viewModel.bmi.value

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen2(
                        navController = navController,
                        onResultClicked = { height, weight ->
                            viewModel.bmiCal(height, weight)
                            navController.navigate("result")
                        }) //onResultClicked가 Data를 받아와야하니까.. 데이터를 담을 ViewModel 작성
                }
                composable("result") {
                    ResultScreen2(navController = navController, bmi = bmi)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen2(
    navController: NavController,
    onResultClicked: (Double, Double) -> Unit
) {
    val (height, setHeight) = rememberSaveable {
        mutableStateOf("")
    }

    val (weight, setWeight) = rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "비만도 계산기") })
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = height,
                onValueChange = setHeight,
                label = { Text(text = "키") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = weight,
                onValueChange = setWeight,
                label = { Text(text = "몸무게") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    if (height.isNotEmpty() && weight.isNotEmpty()) {
                        onResultClicked(height.toDouble(), weight.toDouble())
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "클릭")
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen2(navController: NavController, bmi: Double) {

    val text = when {
        bmi >= 35 -> "고도 비만"
        bmi >= 30 -> "2단계 비만"
        bmi >= 25 -> "1단계 비만"
        bmi >= 23 -> "과체중"
        bmi >= 18.5 -> "정상"
        else -> "저체중"
    }

    val imageRes = when {
        bmi >= 23 -> R.drawable.baseline_sentiment_very_dissatisfied_24
        bmi >= 18.5 -> R.drawable.baseline_sentiment_satisfied_24
        else -> R.drawable.baseline_sentiment_dissatisfied_24
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "비만도 계산기") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "home",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = text, fontSize = 50.sp)
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
            )
        }
    }
}

class BmiViewModel2 : ViewModel() {
    private val _bmi = mutableStateOf(0.0)
    val bmi: State<Double> = _bmi

    fun bmiCal(height: Double, weight: Double) {
        _bmi.value = weight / (height / 100).pow(2.0)
    }
}

// **************************************************************************보지 않고 코딩******************************************************************


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onResultClicked: (Double, Double) -> Unit
) {
    val (height, setHeight) = rememberSaveable {
        mutableStateOf("")
    }
    val (weight, setWeight) = rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("비만도 계산기") }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = height,
                onValueChange = setHeight,
                label = { Text("키") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = weight,
                onValueChange = setWeight,
                label = { Text("몸무게") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (height.isNotEmpty() && weight.isNotEmpty()) {
                        onResultClicked(height.toDouble(), weight.toDouble())
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("결과")
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultScreen(
    navController: NavController,
    bmi: Double,
) {
    val text = when {
        bmi >= 35 -> "고도 비만"
        bmi >= 30 -> "2단계 비만"
        bmi >= 25 -> "1단계 비만"
        bmi >= 23 -> "과체중"
        bmi >= 18.5 -> "정상"
        else -> "저체중"
    }

    val imageRes = when {
        bmi >= 23 -> R.drawable.baseline_sentiment_very_dissatisfied_24
        bmi >= 18.5 -> R.drawable.baseline_sentiment_satisfied_24
        else -> R.drawable.baseline_sentiment_dissatisfied_24
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "비만도 계산기") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "home",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                //colorFilter = (ColorFilter.tint(color = ))
            )

        }
    }
}


class BmiViewModel : ViewModel() {
    private val _bmi = mutableStateOf(0.0)
    val bmi: State<Double> = _bmi

    fun bimCalculate(
        height: Double,
        weight: Double,
    ) {
        _bmi.value = weight / (height / 100.0).pow(2.0) //pow 는 제곱임
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = viewModel<BmiViewModel>()
    val navController = rememberNavController()

    val bmi = viewModel.bmi.value //state 값을 가짐

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen() { height, weight ->
                viewModel.bimCalculate(height, weight)
                navController.navigate("result")

            }
        }
        composable(route = "result") {
            ResultScreen(navController = navController, bmi = bmi)
        }
    }
}