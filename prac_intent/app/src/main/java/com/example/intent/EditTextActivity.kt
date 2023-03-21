package com.example.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.intent.databinding.ActivityEditTextBinding

class EditTextActivity : AppCompatActivity() {

    val binding by lazy { ActivityEditTextBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            editText.addTextChangedListener {
                editable ->
                Log.d("edit Text", "입력된 값 ${editable.toString()}")
                textView.text = editable.toString()
            }
        }
    }
}