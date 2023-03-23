package com.example.tave_android.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tave_android.common.Constant
import com.example.tave_android.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPermissionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnCamera.setOnClickListener { checkPermission() }
        setContentView(binding.root)
    }

    /*
    Description
     - CAMERA 권한을 확인하는 함수
     - val cameraPermission : 카메라 권한을 체크하여 cameraPermission에 저장
     - 조건문을 통해 권한 승인 확인
    */
    private fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (cameraPermission == PackageManager.PERMISSION_GRANTED) { openCamera() }
        else { requestPermission() }
    }

    /*
    Description
     - Camera 기능 실행
    */
    private fun openCamera() = Intent(MediaStore.ACTION_IMAGE_CAPTURE).run { startActivity(this) }


    /*
    Description
     - Camera 권한을 요청
    */
    private fun requestPermission() = ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.CAMERA),
        Constant.CAMERA_PERMISSION_CODE
    )


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
           Constant.CAMERA_PERMISSION_CODE -> {
               if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   openCamera()
               } else {
                   Toast.makeText(this,"권한이 승인되지 않았습니다.", Toast.LENGTH_SHORT).show()
                   finish()
               }
           }
        }
    }
}