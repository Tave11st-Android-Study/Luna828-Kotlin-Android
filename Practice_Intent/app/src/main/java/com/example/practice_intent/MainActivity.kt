package com.example.practice_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.practice_intent.R.id

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity - onCreate() called")

        val nextBtn = findViewById<Button>(R.id.btn1)

        nextBtn.setOnClickListener {
            //Intent 사용 방법
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

        }
    }

}