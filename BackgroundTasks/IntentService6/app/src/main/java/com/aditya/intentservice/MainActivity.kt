package com.aditya.intentservice

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.aditya.intentservice.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> Log.d(TAG, "Permission granted: Notification")
                else -> Log.d(TAG, "Permission denied : Notification")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        binding.button.setOnClickListener {
            startIntentService()
        }

    }

    private fun startIntentService() {
        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra("extra_string", binding.editTextText.text.toString())
        ContextCompat.startForegroundService(this, intent)
    }
}