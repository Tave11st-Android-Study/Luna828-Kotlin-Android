package com.example.new_project_2

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.timer

class stopwatchViewModel : ViewModel() {
    private var time = 0
    //타임을 멈추는 경우에 대비해서 null을 줘야함
    private var timerTask: Timer? = null

    private var lap = 1 //랩 타임 리스트의 순번

    //초의 상태
    private val _sec = mutableStateOf(0)
    val sec: State<Int> = _sec

    //milli 초 상태
    private val _mili = mutableStateOf(0)
    val mili: State<Int> = _mili

    //isRunning - Boolean
    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    //laptime 랩 타임 찍히게 하기 (여러개)
    private val _laptimes = mutableStateOf(mutableListOf<String>())
    val laptimes: State<List<String>> = _laptimes

    fun start() {
        _isRunning.value = true
        timerTask = timer(period = 10) { //period: 0.001초
            time++
            _sec.value = time / 100
            _mili.value = time % 100
        }
    }

    fun pause() {
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset() {
        timerTask?.cancel()

        time = 0
        _isRunning.value = false
        _sec.value = 0
        _mili.value = 0

        _laptimes.value.clear()
        lap = 1
    }

    fun recordLapTime(){
        _laptimes.value.add(0, "$lap LAP : ${sec.value} - ${mili.value}")
        lap++
    }
}