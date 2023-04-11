package com.example.stopwatch_hw

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

    fun start(
        context: Context
    ) {
        _isRunning.value = true
        timerTask = timer(period = 10) {
            time++
            _min.value = _sec.value / 100
            _sec.value = time / 100 //100이 넘고.. 초기화 0으로 하는법..?은?
            _mili.value = time % 100
            if(_sec.value == 1 && _mili.value == 99){
                // 99:99:99가 되었을 때, pause()를 호출하고 Toast를 띄우면될 듯?
                _isRunning.value = false
                timerTask?.cancel()
                CoroutineScope(Dispatchers.Main).launch {
                    //Toast는 UI 스레드가 아닌 스레드에서 토스트 창을 띄우려고 하면
                    // can't toast on a thread that has not called looper.prepare() 에러가 나옴..
                    Toast.makeText(context, "실행을 자동으로 중지합니다", Toast.LENGTH_SHORT).show()
                }
            }
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
        _min.value = 0 //초기화 시켜주기
        _sec.value = 0
        _mili.value = 0
    }
}