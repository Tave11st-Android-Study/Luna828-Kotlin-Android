package com.example.my_webbrowser

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val url = mutableStateOf("https:///www.google.com")

    private val _undoShareFlow = MutableSharedFlow<Boolean>() //Default값 설정하지 않아도 ok
    val undoSharedFlow = _undoShareFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    val redoSharedFlow = _redoSharedFlow.asSharedFlow()
    fun undo() {
        //viewModel 안에서는 viewModelScope를 사용
        viewModelScope.launch {
            _undoShareFlow.emit(true) //동일한 값을 발행할 때 사용하기 좋은 기능
        }
    }

    fun redo() {
        viewModelScope.launch {
            _redoSharedFlow.emit(true) //동일한 값을 발행할 때 사용하기 좋은 기능
    }
}