package com.aditya.staticbroadcastreceiver1

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentSender.OnFinished
import android.hardware.usb.UsbManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HardwarePropertiesManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.aditya.staticbroadcastreceiver1.databinding.ActivityMainBinding
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val receiver = MyStaticReceiver()
    val channelId = "my_channel_id"
    val title = "Notification Title"
    val message = "This is a basic notification."
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when (isGranted) {
            true -> Log.i(TAG, "Permission Granted ")
            false -> Log.i(TAG, "Permission denied ")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate: First")



        requestPermission()
        // Just a dummy Notification
        Notificationer.showNotification(this@MainActivity, channelId, title, message)


        binding.btnSecondActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: First ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: First ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: First ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: First")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: First ")
    }
}