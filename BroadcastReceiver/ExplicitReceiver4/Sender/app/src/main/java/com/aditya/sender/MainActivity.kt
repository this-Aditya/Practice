package com.aditya.sender

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.sender.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            //For Sending Broadcasts to the same app this is more than enough
//            sendBroadcast(Intent(this, ExplicitReceiver1::class.java))

            // For Sending the broadcast to the other app
            val cn = ComponentName(
                "com.aditya.receiver",
                "com.aditya.receiver.ExplicitbroadcastReceiver2")
            intent.setComponent(cn)
            sendBroadcast(intent)
        }
    }
}