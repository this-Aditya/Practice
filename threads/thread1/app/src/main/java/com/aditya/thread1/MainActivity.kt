package com.aditya.thread1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.thread1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var thread1: NewThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnstart.setOnClickListener {
            val seconds = binding.etSeconds.text.toString().toInt()
            thread1 = NewThread(seconds)
            thread1.start()
        }
     binding.btnstop.setOnClickListener {
         
     }
    }


}