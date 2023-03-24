package com.example.tave_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tave_android.databinding.ActivityDesignBinding
import com.example.tave_android.intent_design_Prac.*

class DesignActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityDesignBinding.inflate(layoutInflater) }

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btn.setOnClickListener(this)
        binding.EditText.setOnClickListener(this)
        binding.checkBoxBtn.setOnClickListener(this)
        binding.radioBtn.setOnClickListener(this)
        binding.toggleSwitchBtn.setOnClickListener(this)
        binding.progressBtn.setOnClickListener(this)
        binding.seekBarBtn.setOnClickListener(this)
        binding.ratingBtn.setOnClickListener(this)
        binding.spinnerBtn.setOnClickListener(this)
        binding.recyclerBtn.setOnClickListener(this)

        setContentView(binding.root)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == Activity.RESULT_OK) {
                    it.data?.getStringExtra("returnValue")?.let {
                        message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }




//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        print("${Constant.TAG}ActivityResult 실행")
//
//        if(resultCode == Activity.RESULT_OK){
//            when(requestCode) {
//                99 -> {
//                    data?.getStringExtra("returnValue")?.let {
//                            message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn ->
                Intent(this, IntentActivity::class.java)
                    .apply { putExtra("key", "Hi, i'm Luna") }
                    .run { startActivity(this) }
            R.id.EditText ->
                Intent(this, EditTextActivity::class.java).run { startActivity(this) }
            R.id.checkBoxBtn ->
                Intent(this, CheckBoxActivity::class.java).run { startActivity(this) }
            R.id.radioBtn ->
                Intent(this, RadioButtonActivity::class.java).run { startActivity(this) }
            R.id.toggleSwitchBtn ->
                Intent(this, ToggleSwitchActivity::class.java).run { startActivity(this) }
            R.id.progressBtn ->
                Intent(this, ProgressbarActivity::class.java).run { startActivity(this) }
            R.id.seekBarBtn ->
                Intent(this, SeekBarActivity::class.java).run { startActivity(this) }
            R.id.ratingBtn ->
                Intent(this, RatingBarActivity::class.java).run { startActivity(this) }
            R.id.spinnerBtn ->
                Intent(this, SpinnerActivity::class.java).run { startActivity(this) }
            R.id.recyclerBtn ->
                Intent(this, RecyclerViewActivity::class.java).run { startActivity(this) }
        }
    }
}