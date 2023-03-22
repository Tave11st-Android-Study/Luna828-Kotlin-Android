package com.example.tave_android.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tave_android.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    val binding by lazy { ActivityPermissionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            checkPermission()
        }
    }

    fun checkPermission() {
        //권한을 확인하는 함수 만들기
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) //카메라 권한이 무엇인지 체크하는 변수 cameraPermisson에 저장가능

        if (cameraPermission == PackageManager.PERMISSION_GRANTED) { //승인 된 상태
            openCamera()
        } else {
            requestPermission()
        }
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
        //Toast.makeText(this, "카메라를 실행합니다", Toast.LENGTH_SHORT).show()
    }

    //권한을 만들어서 요청 (권한 요청같은 경우는 다른 곳 에서도 쓰일 수 있으니, 메서드로 만든 것)
    fun requestPermission() {
        //실제 권한 요청 코드
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
           99 -> {
               if(grantResults[0] == PackageManager.PERMISSION_GRANTED) { //permission_granted 승인
                   openCamera()
               } else {
                   Toast.makeText(this,"권한을 승인하지 않으면 앱이 종료됩니다", Toast.LENGTH_SHORT).show()
                   finish()
               }
           }
        }
    }
}