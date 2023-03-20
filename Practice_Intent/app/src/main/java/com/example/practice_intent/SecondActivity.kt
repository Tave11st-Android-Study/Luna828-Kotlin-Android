package com.example.practice_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val secondPage = findViewById<TextView>(R.id.secondPage)

        secondPage.text = intent.getStringExtra("key")

//        if(intent.hasExtra("key")) {
//         secondPage.text = intent.getStringExtra("key")
//        }

    }
}