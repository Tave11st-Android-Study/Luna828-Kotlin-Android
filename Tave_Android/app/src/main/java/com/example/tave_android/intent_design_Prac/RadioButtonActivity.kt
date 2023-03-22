package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tave_android.R
import com.example.tave_android.databinding.ActivityRadioButtonBinding

class RadioButtonActivity : AppCompatActivity() {

    val binding by lazy { ActivityRadioButtonBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.radioButton.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.radioApple -> { Log.d("라디오버튼", "사과가 선택되었습니다") }
                R.id.radioBanana -> { Log.d("라디오버튼", "바나나가 선택되었습니다") }
                R.id.radioOrange -> { Log.d("라디오버튼", "오렌지가 선택되었습니다") }
            }
        }
    }
}