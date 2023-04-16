package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.example.tave_android.Dao.memoDataBase
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.permission.PermissionActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var memoDB: memoDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.designBtn.setOnClickListener(this)
        binding.permissionBtn.setOnClickListener(this)
        binding.fragment.setOnClickListener(this)

        val customView = CustomView(this)

        setContentView(binding.root)

        CoroutineScope(Dispatchers.Default).launch {
            launch {
                for (i in 0..10) {
                    Log.d("로그1", "$i")
                }
            }

            launch {
                for (i in 0..10) {
                    Log.d("로그2", "$i")
                }
            }.join()
        }

        //Room ORM 사용방법
        memoDB = Room.databaseBuilder(this, memoDataBase::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.designBtn ->
                Intent(this, DesignActivity::class.java).run { startActivity(this) }
            R.id.permissionBtn ->
                Intent(this, PermissionActivity::class.java).run { startActivity(this) }
            R.id.fragment ->
                Intent(this, FragmentActivity::class.java).run { startActivity(this) }
        }
    }
}