package com.example.tave_android.intent_design_Prac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tave_android.databinding.ActivityProgressbarBinding
import kotlin.concurrent.thread

class ProgressbarActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProgressbarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            // <- 메인 스레드
            showProgress(true)
            thread(start=true) { /// -> 서브 스레드
                Thread.sleep(3000)
                // 화면에 영향을 미치는 코드는 메인 스레드로 다시 보내야 한다
                runOnUiThread {
                    showProgress(false)
                    //progressLayout.visibility = View.GONE
                }

            } // <- 서브 스레드 영역
            /// <- 메인 스레드
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressLayout.visibility = if(show) View.VISIBLE else View.GONE
    }
}