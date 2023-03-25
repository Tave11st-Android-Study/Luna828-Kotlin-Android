package com.example.tave_android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomView(context: Context) : View(context){

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //text 출력을 위한 것
        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 100f

        //그림을 그려주기 = canvas
        canvas?.run {
            drawText("안녕하세요", 0f, 100f, paint) //0f, 0f 텍스트의 위치
        }
    }
}