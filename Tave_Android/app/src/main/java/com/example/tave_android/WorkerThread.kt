package com.example.tave_android

import android.util.Log

class WorkerThread : Thread() {

    override fun run() {
        super.run()
        var i = 0
        while(i < 10) {
            i += 1
            try {
                Thread.sleep(1000)
            }catch(exception: NumberFormatException){
                0
            }
            Log.i("WorkerThread", "$i")
        }
    }
}