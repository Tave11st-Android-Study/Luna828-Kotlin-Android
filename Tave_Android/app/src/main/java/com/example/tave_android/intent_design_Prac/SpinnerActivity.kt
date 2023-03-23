package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.tave_android.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySpinnerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        //spinner에 필요한 데이터를 가상으로 만들기
        val data = listOf("-선택하세요-", "1월", "2월", "3월")

        //스피너 컨테이너에 넣기 위해서는 중간에 매개 역할을 하는 Adapter
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        with(binding){
            spinner.adapter = adapter

            //textView에 spinner의 list를 뿌려주기 위해서는?
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val seleted = data.get(position)
                    result.text = seleted
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
        }

    }
}