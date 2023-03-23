package com.example.tave_android.intent_design_Prac

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityIntentBinding

class IntentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityIntentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.secondPage.text = intent.getStringExtra("key")

        //암시적 인텐트 : 웹 브라우저 호출, 이메일 전송, 전화 앱으로 통화
        binding.naverUrl.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
                .run { startActivity(this) }
        }

        /* TODO : 안쓰는 코드 인가요? 안쓰는 코드면 삭제!
        if(intent.hasExtra("key")) {
         secondPage.text = intent.getStringExtra("key")
        }
        */
    }
}