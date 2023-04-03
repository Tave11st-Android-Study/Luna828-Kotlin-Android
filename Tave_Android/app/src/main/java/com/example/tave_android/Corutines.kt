package com.example.tave_android

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Corutines : Thread() {

    fun corutine() {
        repeat(3){
            GlobalScope.launch {
                println("Hi from ${Thread.currentThread()}")
                for (i in 1..50){
                    println("corutine연습 + $i")
                }
            }
        }
    }

}