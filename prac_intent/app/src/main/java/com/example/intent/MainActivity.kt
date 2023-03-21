package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intent.EditTextActivity
import com.example.intent.databinding.ActivityMainBinding
import com.example.practice_intent.SecondActivity

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "MainActivity - onCreate() called")

        binding.btn.setOnClickListener {
            //Intent 사용 방법
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.EditText.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            startActivity(intent)
        }
    }

}