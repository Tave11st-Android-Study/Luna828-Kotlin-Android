package com.example.tave_android

import android.util.Log

class RunnableThread : Runnable {
    override fun run() {
        var i = 0
        while(i < 10){
            i++
            Log.i("Runnable", "$i")
        }
    }
}