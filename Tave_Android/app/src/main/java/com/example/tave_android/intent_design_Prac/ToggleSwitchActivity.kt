package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityToggleSwitchBinding

class ToggleSwitchActivity : AppCompatActivity() {

    val binding by lazy { ActivityToggleSwitchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
                textToggle.text = if(isChecked) "On" else "Off"
                //if(isChecked) textToggle.text = "On" else textToggle.text = "Off"
            }

            switchButton.setOnCheckedChangeListener { compoundButton, isChecked ->
                textSwitch.text = if(isChecked) "On" else "Off"
            }
        }
    }
}