package com.aditya.sleep

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.aditya.sleep.databinding.ActivityMainBinding
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var permissioner: Permissioner
    private lateinit var pendingIntent: PendingIntent


    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    }
    else if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        arrayOf("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    }
    else {
            arrayOf(Manifest.permission.ACTIVITY_RECOGNITION)
    }

    private val permissionsLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            handlePermissionsResult(permissions)
        }

    private fun handlePermissionsResult(permissions: Map<String, Boolean>) {
        for ((permission, isGranted) in permissions) {
            when (isGranted) {
                true -> Log.i(TAG, "Permission Granted: $permission")
                false -> Log.i(TAG, "Permission denied: $permission")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        permissioner = Permissioner()
        if (!allPermissionsApproved()){
        requestPermissionss()
        }

        binding.btnStartTracking.setOnClickListener {
            startSleepSleepTracking()
        }

        binding.btnStopTracking.setOnClickListener {
            stopSleeptracking()
        }
    }

    private fun stopSleeptracking() {
        val intent = Intent(this, SleepService::class.java)
        stopService(intent)
    }

    private fun startSleepSleepTracking() {
        val intent = Intent(this, SleepService::class.java)
        ContextCompat.startForegroundService(this,intent)
    }

    private fun requestPermissionss() {
        Log.i(TAG, "requestActivityRecognitionPermission: ")
        val message =
            "For the sleep Tracking these permissions are necessary, tap Yes to grant permissions."

         permissioner.requestPermissions(
                this, permissions, permissionsLauncher,
                message
            )
        }


// This is way to register in an actiity. Here Actually service is used to register for sleep updates
// This message is never used here
    @SuppressLint("MissingPermission")
    private fun requestSleepUpdates() {
        Log.i(TAG, "requestSleepUpdates---- ")
        lifecycleScope.launch(Dispatchers.Default) {
            if (permissionsApprovedspecific(Manifest.permission.ACTIVITY_RECOGNITION)) {
                Log.i(TAG, "Permission approved for activity recognition.")
                ActivityRecognition.getClient(this@MainActivity)
                    .requestSleepSegmentUpdates(
                        pendingIntent,
                        SleepSegmentRequest.getDefaultSleepSegmentRequest()
                    )
                    .addOnSuccessListener {
                        Log.d(TAG, "Successfully subscribed to sleep data.")
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "Exception when subscribing to sleep data: $exception")
                    }
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun permissionsApprovedspecific(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    }


    fun allPermissionsApproved(): Boolean {
        return permissions.any { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}