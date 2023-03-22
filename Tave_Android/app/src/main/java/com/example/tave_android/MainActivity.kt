package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.intent_design_Prac.*
import com.example.tave_android.permission.PermissionActivity
import java.security.Permission

class MainActivity : AppCompatActivity() {
    val TAG: String = "로그"

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.designBtn.setOnClickListener {
            val intent = Intent(this, DesignActivity::class.java)
            startActivity(intent)
        }

        binding.permissionBtn.setOnClickListener {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
    }
}