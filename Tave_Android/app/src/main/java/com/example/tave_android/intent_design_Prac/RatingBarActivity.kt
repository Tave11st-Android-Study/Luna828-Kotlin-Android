package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityCheckBoxBinding
import com.example.tave_android.databinding.ActivityRatingBarBinding

class RatingBarActivity : AppCompatActivity() {

    val binding by lazy { ActivityRatingBarBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if(fromUser){
                    textView2.text = "${rating}"
                }
            }
        }
    }
}

// xml 파일에서 numStars를 찾으면 별 개수를 늘릴 수 있음 