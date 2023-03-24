package com.example.prac_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prac_fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val aFragment by lazy { AFragment() }
    private val bFragment by lazy { BFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.BtnA.setOnClickListener {
            setFrag(0)
        }
        binding.BtnB.setOnClickListener {
            setFrag(1)
        }

    }

    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum){
            0 -> ft.replace(R.id.fragmentList, AFragment()).commit()
            1 -> ft.replace(R.id.fragmentList, BFragment()).commit()
        }
    }


//    fun GoAFragment() {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragmentList, aFragment)
//        transaction.addToBackStack("a")
//        transaction.commit()
//    }
//
//    fun GoBFragment() {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragmentList, bFragment)
//        transaction.addToBackStack("b")
//        transaction.commit()
//    }
}