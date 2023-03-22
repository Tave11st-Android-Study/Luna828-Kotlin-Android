package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityDesignBinding
import com.example.tave_android.intent_design_Prac.*

class DesignActivity : AppCompatActivity() {

    val binding by lazy { ActivityDesignBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            //Intent 사용 방법
            val intent = Intent(this, IntentActivity::class.java)
            intent.putExtra("key", "Hi, I'm Luna")
            startActivity(intent)
        }

        binding.EditText.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            startActivity(intent)
        }

        binding.checkBoxBtn.setOnClickListener {
            val intent = Intent(this, CheckBoxActivity::class.java)
            startActivity(intent)
        }

        binding.radioBtn.setOnClickListener {
            val intent = Intent(this, RadioButtonActivity::class.java)
            startActivity(intent)
        }

        binding.toggleSwitchBtn.setOnClickListener {
            val intent = Intent(this, ToggleSwitchActivity::class.java)
            startActivity(intent)
        }

        binding.progressBtn.setOnClickListener {
            val intent = Intent(this, ProgressbarActivity::class.java)
            startActivity(intent)
        }
    }
}