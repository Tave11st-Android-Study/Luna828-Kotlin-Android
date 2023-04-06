package com.example.my_webbrowser

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val url = mutableStateOf("https:///www.google.com")

    /*Flow란?
    값을 순차적으로 내보내고 정상적으로 또는 예외적으로 완료되는 비동기 데이터 스트림
    MutableSharedFlow 로 Flow를 만들 수 있으며 map, filter, take, zip 과 같은 다양한 오퍼레이터를 지원
    */
    private val _undoShareFlow = MutableSharedFlow<Boolean>() //Default값 설정하지 않아도 ok
    val undoSharedFlow = _undoShareFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()
    fun undo() {
        //viewModel 안에서는 viewModelScope를 사용
        viewModelScope.launch {
            _undoShareFlow.emit(true) //동일한 값을 발행할 때 사용하기 좋은 기능
            //emit은 flow context를 유지하기 위해 dispatcher를 엄격하게 관리한다.
        }
    }

    fun redo() {
        viewModelScope.launch {
            _redoSharedFlow.emit(true) //동일한 값을 발행할 때 사용하기 좋은 기능
        }
    }
}