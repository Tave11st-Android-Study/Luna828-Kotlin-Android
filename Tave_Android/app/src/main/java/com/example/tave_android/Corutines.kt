package com.example.tave_android

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Corutines : Thread() {

    fun corutine() {
        repeat(1){
            GlobalScope.launch {
                /*
                * GlobalScope는 어플리케이션의 생명주기와 함께 동작하기 때문에 별도의 생명주기 관리 필요 X
                * 시작부터 종료될 때까지 장시간 실행되어야하는 코루틴이 있다면 GlobalScope 사용 권장
                * */
                for (i in 1..3){
                    println("${System.currentTimeMillis() /1000} corutine1 + $i")
                    delay(2000)
                }
            }
        }
    }

    fun corutine2() {
        val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
        println("시작")
        repeat(1){
            GlobalScope.launch {
                for (i in 1..3){
                    println("${System.currentTimeMillis() /1000} corutine2 + $i")
                    delay(3000)
                }
            }
        }
    }

}