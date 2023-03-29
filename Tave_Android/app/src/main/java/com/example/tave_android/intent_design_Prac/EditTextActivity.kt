package com.example.tave_android.intent_design_Prac

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.tave_android.common.Constant
import com.example.tave_android.databinding.ActivityEditTextBinding

class EditTextActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditTextBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //EditText 사용법
        with(binding){
            editText.addTextChangedListener {
                editable -> Log.d(Constant.TAG + "에딧 텍스트", "입력 값 ${editable.toString()}")
                textView.text = editable.toString()

                btnClose.setOnClickListener{
                    val returnIntent = Intent()
                    val message = editText.text.toString()
                    returnIntent.putExtra("returnValue", message)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }
            }
        }
    }
}