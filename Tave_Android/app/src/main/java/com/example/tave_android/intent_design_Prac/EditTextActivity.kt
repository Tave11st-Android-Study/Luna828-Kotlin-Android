package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.tave_android.databinding.ActivityEditTextBinding

class EditTextActivity : AppCompatActivity() {

    val binding by lazy { ActivityEditTextBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //EditText 사용법
        with(binding){
            editText.addTextChangedListener {
                editable -> Log.d("에딧 텍스트", "입력 값 ${editable.toString()}")
                textView.text = editable.toString()
            }
        }
    }
}