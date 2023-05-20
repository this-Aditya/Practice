package com.aditya.service3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.service3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                binding.tvServiceInfo.text = "Service Started"
            }
        }

        binding.btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                binding.tvServiceInfo.text = "Service Stopped"
            }
        }

        binding.btnSend.setOnClickListener {
            Intent(this, MyService::class.java).also {
                it.putExtra(EXTRA_STRING, binding.etServiceText.text.toString())
                startService(it)
                binding.tvServiceInfo.text = "data Sent to service"
            }
        }
    }

    companion object{
        const val EXTRA_STRING = "com.aditya.service3.EXTRA_DATA"
    }
}