package com.aditya.staticbroadcastreceiver1

import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.staticbroadcastreceiver1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Second ")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener { 
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Second ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Second ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Second ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Second")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Second ")
    }
}