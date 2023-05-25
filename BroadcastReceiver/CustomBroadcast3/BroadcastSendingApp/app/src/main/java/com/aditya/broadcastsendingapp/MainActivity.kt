package com.aditya.broadcastsendingapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aditya.broadcastsendingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            send()
        }

         customReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    CUSTOM_ACTION -> {
                        binding.textView.visibility = View.VISIBLE
                        binding.textView.text = intent.getStringExtra(CUSTOM_EXTRA_STRING)
                    }
                }
            }
        }

        val filter = IntentFilter().also {
            it.addAction(CUSTOM_ACTION)
        }
        registerReceiver(customReceiver, filter)



    }

    override fun onStart() {
        super.onStart()
        binding.textView.visibility = View.INVISIBLE
    }

    private fun send() {
        val intent = Intent()
        intent.action = CUSTOM_ACTION
        val extraData = binding.editTextText.text.toString()
        intent.putExtra(CUSTOM_EXTRA_STRING, extraData)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(customReceiver)
    }

    companion object {
        const val CUSTOM_ACTION = "com.aditya.broadcasts.CUSTOM_ACTION"
        const val CUSTOM_EXTRA_STRING = "com.aditya.broadcasts.CUSTOM_STRING"
    }
}