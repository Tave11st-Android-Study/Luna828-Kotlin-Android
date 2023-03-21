package com.example.tave_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityIntentBinding
import com.example.tave_android.databinding.ActivityMainBinding

class IntentActivity : AppCompatActivity() {

    val binding by lazy { ActivityIntentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}