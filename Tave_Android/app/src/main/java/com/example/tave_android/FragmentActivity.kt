package com.example.tave_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tave_android.databinding.ActivityFragmentBinding
import com.example.tave_android.fragment.DetailFrament
import com.example.tave_android.fragment.ListFragment

class FragmentActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFragmentBinding.inflate(layoutInflater) }
    private val listFragment by lazy { ListFragment() }
    private val detailFragment by lazy { DetailFrament() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()
    }

    fun goDetail() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayOut, detailFragment)
        transaction.addToBackStack("detail")
        // 뒤로가기시 액티비티가 종료가 되어버리는데 그 것을 방지하고자 addToBackStack 사용하고 액티비티를 하나 더 띄우는 것처럼 fragment 동작을 스택에다 넣어둠
        transaction.commit()
    }

    fun goBack() {
        onBackPressed() //뒤로 가기 명령어가 담겨져 있음
    }

    fun setFragment() {

        val bundle = Bundle()
        bundle.putString("key1", "List Fragment")
        bundle.putInt("key2", 20230325)

        listFragment.arguments = bundle

        // 1. 사용할 프래그먼트 생성
        // 2. 트랜잭션 생성
        val transaction = supportFragmentManager.beginTransaction()
        // 3. 트랜잭션을 통해 프래그먼트 삽입
        transaction.add(R.id.frameLayOut, listFragment)
        transaction.commit() //완료되었다 라고 알려주기
    }
}