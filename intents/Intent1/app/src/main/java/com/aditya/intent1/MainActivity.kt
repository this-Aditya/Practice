package com.aditya.intent1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.intent1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.button.setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("first",1)
                    putExtra("second",2)
                    putExtra("third",3)
                }
                startActivity(intent)
            }
    }
}