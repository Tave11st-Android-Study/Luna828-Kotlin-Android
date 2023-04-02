package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.permission.PermissionActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.designBtn.setOnClickListener(this)
        binding.permissionBtn.setOnClickListener(this)
        binding.fragment.setOnClickListener(this)

        val customView = CustomView(this)

        setContentView(binding.root)

        binding.frameLayout.addView(CustomView(this))

        Log.d("1이다", "1")

        var thread = WorkerThread()
        thread.start()

//        var runnable = Thread(RunnableThread) // (RunnableThread 빨간줄 에러)
//        Error: Classifier 'RunnableThread' does not have a companion object, and thus must be initialized here
//        runnable.start()

        Thread{
            var i = 0
            while (i < 10) {
                i++
                Log.i("람다식으로 Runnable 익명 객체 구현", "$i")
            }
        }.start()

        Log.d("2이다", "2")

        thread(start = true){
            var i = 10
            while(i <= 10) {
                i--
                if(i == 0){
                    break
                }
                try {
                    Thread.sleep(2000)
                }catch (exception: NumberFormatException){
                    0
                }
                Log.i("Kotlin에서 제공하는 thread", "$i")
            }
        }

    }

    override fun onClick(view: View?) {
        when(view?.id) {
           R.id.designBtn ->
               Intent(this, DesignActivity::class.java).run { startActivity(this) }
           R.id.permissionBtn ->
               Intent(this, PermissionActivity::class.java).run { startActivity(this) }
           R.id.fragment ->
               Intent(this, FragmentActivity::class.java).run{startActivity(this)}
        }
    }
}