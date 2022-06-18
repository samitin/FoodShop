package ru.samitin.foodshop.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.samitin.foodshop.R
import ru.samitin.foodshop.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}