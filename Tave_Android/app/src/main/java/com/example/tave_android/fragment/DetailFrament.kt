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
    lateinit var binding: FragmentDetailFramentBinding
    lateinit var fragmentActivity: FragmentActivity

    override fun onAttach(context: Context) { //context : 나를 삽입한 액티비티가 담김
        super.onAttach(context)

        if(context is FragmentActivity) fragmentActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailFramentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            fragmentActivity.goBack()
        }
    }

}