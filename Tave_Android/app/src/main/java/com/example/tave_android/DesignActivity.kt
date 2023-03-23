package com.example.tave_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tave_android.databinding.ActivityDesignBinding
import com.example.tave_android.intent_design_Prac.*

class DesignActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityDesignBinding.inflate(layoutInflater) }

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

        setContentView(binding.root)
    }

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
        }
    }
}