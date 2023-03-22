package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.tave_android.R
import com.example.tave_android.databinding.ActivityCheckBoxBinding

class CheckBoxActivity : AppCompatActivity() {

    val binding by lazy { ActivityCheckBoxBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            checkApple.setOnCheckedChangeListener(checkBoxListener) //리스너를 위에 만들고 사용
            checkBanana.setOnCheckedChangeListener(checkBoxListener) //리스너를 위에 만들고 사용
            checkOrange.setOnCheckedChangeListener(checkBoxListener) //리스너를 위에 만들고 사용


//            checkBanana.setOnCheckedChangeListener { buttonView, isChecked -> //buttonView : 선택한 상태가 넘어옴
//                if(isChecked) Log.d("체크박스", "바나나가 선택되었습니다")
//                else Log.d("체크박스", "바나나가 선택해제 되었습니다")
//            }
//
//            checkOrange.setOnCheckedChangeListener { buttonView, isChecked -> //buttonView : 선택한 상태가 넘어옴
//                if(isChecked) Log.d("체크박스", "오렌지가 선택되었습니다")
//                else Log.d("체크박스", "오렌지가 선택해제 되었습니다")
//            }
        }
    }

    //리스너 만들기
    //코드를 구현 해야 되는 깡통 함수를 인터페이스라고 한다
    val checkBoxListener = CompoundButton.OnCheckedChangeListener { checkBox, isChecked ->

        when(checkBox.id) {
            R.id.checkApple -> {
                if(isChecked) Log.d("체크박스", "사과가 선택되었습니다")
                else Log.d("체크박스", "사과가 선택해제 되었습니다")
            }

            R.id.checkBanana -> {
                if(isChecked) Log.d("체크박스", "바나나가 선택되었습니다")
                else Log.d("체크박스", "바나나가 선택해제 되었습니다")
            }

            R.id.checkOrange -> {
                if(isChecked) Log.d("체크박스", "오렌지가 선택되었습니다")
                else Log.d("체크박스", "오렌지가 선택해제 되었습니다")
            }
        }
    }
}