package com.example.my_webbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_webbrowser.ui.theme.My_WebBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            HomeScreen(viewModel = viewModel)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel) {

    //keyboard의 포커스를 잃게해서 키보드를 숨기는 기능
    val focusManager = LocalFocusManager.current

    val (inputUrl, setUrl) = rememberSaveable {
        mutableStateOf("https://www.google.com")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "나만의 웹 브라우저")},
                actions = {
                    IconButton(onClick = {

                    }) {
                       Icon(
                           imageVector = Icons.Default.ArrowBack,
                           contentDescription = "back",
                           tint = Color.White)
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "forward",
                            tint = Color.White)
                    }
                }
            )
    }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = inputUrl,
                onValueChange = setUrl,
                label = { Text(text = "https://")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.url.value = inputUrl // viewModel의 url을 inputUrl로 맞춰주기
                    focusManager.clearFocus() //키보드가 내려가는 효과내기
                })
            )
            Spacer(modifier = Modifier.height(16.dp))

            MyWebView(viewModel = viewModel)
        }
    }
}

@Composable
fun MyWebView(
    viewModel: MainViewModel,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
                  WebView(it).apply {
                      settings.javaScriptEnabled = true //가장 많이 하는 설정
                      webViewClient = WebViewClient()
                      loadUrl("https://google.com")
                  }
        },
        update = {
            webView -> webView.loadUrl(viewModel.url.value) //naver.com 변경시, 브라우저가 바뀌게됨
        },
    )
}


// WebView : 앱 내에서 웹페이지를 표시하는 데 사용됨
// 사용하기 위해서는 Menifest.xml 에  <uses-permission android:name="android.permission.INTERNET"/> 추가해줘야함
// compose 에서는 AndoridView(factory = {WebView(it).apply { loadUrl()}}) loadUrl() 메서드로 웹사이트를 호출 할 수 있다.
// settings.javaScriptEnabled = true 음.. 왜하는걸까요?
// 공부출처 블로그 : https://ddolcat.tistory.com/597