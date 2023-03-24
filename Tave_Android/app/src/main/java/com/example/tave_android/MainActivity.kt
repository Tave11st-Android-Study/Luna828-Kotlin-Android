package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tave_android.databinding.ActivityMainBinding
import com.example.tave_android.permission.PermissionActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.designBtn.setOnClickListener(this)
        binding.permissionBtn.setOnClickListener(this)
        binding.fragment.setOnClickListener(this)

        setContentView(binding.root)
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