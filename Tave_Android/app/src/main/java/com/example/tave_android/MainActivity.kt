package com.example.tave_android

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.permission.PermissionActivity
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        Thread.sleep(5000)
        println("")
        println("")
        Corutines().corutine() //GlobalScope.launch 사용
        Corutines().corutine2()

//        CoroutineScope(Dispatchers.Main).launch { // 어디에다가 실습해봐야 에러가 안날까요?
//            TODO("코드 1")
//            subRoutine()
//            TODO("코드 2")
//        }
    }

    suspend fun subRoutine(){
        for(i in 0..10){
            Log.d("sub Routine", "$i")
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