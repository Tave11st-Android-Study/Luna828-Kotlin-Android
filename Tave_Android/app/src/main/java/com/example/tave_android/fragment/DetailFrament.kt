package com.example.tave_android.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tave_android.FragmentActivity
import com.example.tave_android.R
import com.example.tave_android.databinding.FragmentDetailFramentBinding
import com.example.tave_android.databinding.FragmentListBinding

class DetailFrament : Fragment() {
    private var _bingding: FragmentDetailFramentBinding? = null

    private val binding get() = _bingding!!
    lateinit var fragmentActivity: FragmentActivity

    override fun onAttach(context: Context) { //context : 나를 삽입한 액티비티가 담김
        super.onAttach(context)

        if(context is FragmentActivity) fragmentActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bingding = FragmentDetailFramentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            fragmentActivity.goBack()
        }
    }

    //Fragment에서 View Binding을 사용할 경우 Fragment는 View보다 오래 지속되어, Fragment Lifecyle로 인해 메모리 누수가 발생할 수 있음
    //그래서 반드시 binding 변수를 onDestroyView() 이후에 null로 만들어 줘야함!
    //Fragment 재사용을 위해 View들을 메모리에 보관하도록 Fragment의 동작 변경
    override fun onDestroyView() {
        super.onDestroyView()
        _bingding = null
    }

}