package com.aditya.helloservice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.helloservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
            binding.tvInfo.setText(getString(R.string.service_started))
                startService(it)
            }
        }
        binding.btnStopService.setOnClickListener {
            binding.tvInfo.setText(getString(R.string.service_stopped))
            MyIntentService.stopService()
        }
    }
}