package com.example.practice_intent

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.intent.R

class SecondActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val secondPage = findViewById<TextView>(R.id.secondPage)
        val url = findViewById<Button>(R.id.naverUrl)

        secondPage.text = intent.getStringExtra("key")

        //암시적 인텐트 : 웹 브라우저 호출, 이메일 전송, 전화 앱으로 통화
        url.setOnClickListener {
            val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
            startActivity(intentUrl)
        }

//        if(intent.hasExtra("key")) {
//         secondPage.text = intent.getStringExtra("key")
//        }

    }
}