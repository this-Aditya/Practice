package com.aditya.appforegroundservices

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.aditya.appforegroundservices.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    // Not paying much attentions on permission granting assuming permissions are granted every time whenever asked.
    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted->
            if (isGranted) {
                Log.d(TAG, "From Launcher: Permission granted")
            }
            else {
                Log.d(TAG, "From Launcher: Permission denied")
            }
        }

    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        binding.btnStartService.setOnClickListener {
            startForeService()
        }
        binding.btnStopService.setOnClickListener {
            stopForeService()
        }
    }

    private fun startForeService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        serviceIntent.putExtra(EXTRA_INPUT, binding.etInput.text?.toString() ?: "Nothing Passed")
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun stopForeService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        stopService(serviceIntent)
    }

    companion object {
        const val EXTRA_INPUT = "com.aditya.appforegroundservices.EXTRA_INPUT"
    }
}