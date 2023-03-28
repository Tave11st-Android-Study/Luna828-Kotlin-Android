package com.example.jetpack

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _value = mutableStateOf("Hello World")
    val value: State<String> = _value

    fun changeValue(value: String){
        _value.value = value
    }
}