package com.example.stopwatch_hw

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class MainViewModel : ViewModel() {
    private var time = 0
    private var timerTask: Timer? = null

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    private val _min = mutableStateOf(0)
    val min: State<Int> = _min

    private val _sec = mutableStateOf(0)
    val sec: State<Int> = _sec

    private val _mili = mutableStateOf(0)
    val mili: State<Int> = _mili

    fun start() {
        _isRunning.value = true
        timerTask = timer(period = 10) {
            time++
            _min.value = _sec.value / 100
            _sec.value = time / 100 //100이 넘고.. 초기화 0으로 하는법..?은?
            _mili.value = time % 100
        }
    }

    fun pause() {
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset() {
        timerTask?.cancel()
        _isRunning.value = false
        _min.value = 0 //초기화 시켜주기
        _sec.value = 0
        _mili.value = 0
    }
}