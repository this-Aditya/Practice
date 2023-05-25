package com.aditya.broadcastreceivingapp

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.broadcastreceivingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val receiver = CustomBroadcastReceiver()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        IntentFilter().also {
            it.addAction(CUSTOM_ACTION)
            registerReceiver(receiver, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    companion object{
        const val CUSTOM_ACTION = "com.aditya.broadcasts.CUSTOM_ACTION"
        const val CUSTOM_EXTRA_STRING = "com.aditya.broadcasts.CUSTOM_STRING"
    }
}