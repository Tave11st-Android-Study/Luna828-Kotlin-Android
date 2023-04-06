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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_webbrowser.ui.theme.My_WebBrowserTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    val scaffoldState = rememberScaffoldState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "나만의 웹 브라우저")},
                    actions = {
                        IconButton(onClick = {
                            //IconButton을 클릭했을 때 무엇을 할것인지!
                            viewModel.undo()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back",
                                tint = Color.White)
                        }
                        IconButton(onClick = {
                            viewModel.redo()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "forward",
                                tint = Color.White)
                        }
                    }
                )
            },
            scaffoldState = scaffoldState
        ) {
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

                MyWebView(viewModel = viewModel, scaffoldState = scaffoldState)
            }
        }
}

@Composable
fun MyWebView(
    viewModel: MainViewModel,
    scaffoldState: ScaffoldState
) {
    val webView = rememberWebView()
    //key1이 여기에 전달하는 객체가 변경이 되었을 때, block이 실행이 됨
    LaunchedEffect(Unit){
        viewModel.undoSharedFlow.collectLatest {
            if(webView.canGoBack()){
                webView.goBack()
            } else{
                //snackbar를 띄우기 위해 scaffoldState가 필요했던 것!
                scaffoldState.snackbarHostState.showSnackbar("더 이상 뒤로 갈 수 없음")
            }
        }
    }


    //key1이 여기에 전달하는 객체가 변경이 되었을 때, block이 실행이 됨
    LaunchedEffect(Unit){
        viewModel.redoSharedFlow.collectLatest {
            if(webView.canGoForward()){
                webView.goForward()
            } else{
                //snackbar를 띄우기 위해 scaffoldState가 필요했던 것!
                scaffoldState.snackbarHostState.showSnackbar("더 이상 앞으로 갈 수 없음")
            }
        }
    }


    AndroidView(
        //factory와 update는 coroutineScope가 아님
        //shareflow는 coroutineScope안에서 실행해야함
        modifier = Modifier.fillMaxSize(),
        factory = { webView },
        update = {
            webView -> webView.loadUrl(viewModel.url.value) //naver.com 변경시, 브라우저가 바뀌게됨 (화면 갱신부분)
        },
    )
}

@Composable
fun rememberWebView(): WebView {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true //가장 많이 하는 설정
            webViewClient = WebViewClient()
            loadUrl("https://google.com")
        }
    }
    return webView
}


// WebView : 앱 내에서 웹페이지를 표시하는 데 사용됨
// 사용하기 위해서는 Menifest.xml 에  <uses-permission android:name="android.permission.INTERNET"/> 추가해줘야함
// compose 에서는 AndoridView(factory = {WebView(it).apply { loadUrl()}}) loadUrl() 메서드로 웹사이트를 호출 할 수 있다.
// settings.javaScriptEnabled = true 음.. 왜하는걸까요?
// 공부출처 블로그 : https://ddolcat.tistory.com/597