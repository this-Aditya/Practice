package com.aditya.intent1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.intent1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        processIntent(intent)
    }

    fun processIntent(intent: Intent){
        when {
            intent.getIntExtra("first", -1) == 1 -> {
                println("First when statement")
            }
            intent.getIntExtra("second", -1) == 2 -> {
                println("Second when statement")
            }
            intent.getIntExtra("third", -3) == 3 -> {
                println("Third when statement")
            }
        }
    }
}