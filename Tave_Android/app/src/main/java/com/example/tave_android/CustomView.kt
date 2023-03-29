package com.example.tave_android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
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
            customDrawCircle(canvas)
            customDrawRect(canvas)
        }
    }

    //함수를 빼는 방법
    fun customDrawCircle(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE

        canvas.drawCircle(150f, 300f, 100f, paint)
    }

    fun customDrawRect(canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        paint.color = Color.GREEN

        val rect = Rect(50,450, 250, 650)
        val rect2 = RectF(50f,450f, 250f, 650f)

        canvas.drawRect(rect, paint)
        canvas.drawRect(rect2, paint)
    }
}