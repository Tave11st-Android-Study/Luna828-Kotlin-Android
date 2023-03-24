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

/*
FragmentTransaction.replace() 와 add()의 차이
fragmentTransaction.replace(int containerViewId, Fragment fragment, String tag)
fragmentTransaction.add(int containerViewId, Fragment fragment, String tag)
 는 사용 방법은 비슷하다
 replace()는 Container에 추가되었던 기존 프래그먼트를 바꾸는 역할을 한다.
 즉, 동일한 containerViewId로 추가 했던 모든 프래그먼트에 대해서 remove 호출하고 새로운 프래그먼트를 추가

 add는 remove하는 것이 아닌 그 위에 프래그먼트를 추가하는 것 그러므로 화면이 겹쳐보일 수 있다는 것이 특징
징*/