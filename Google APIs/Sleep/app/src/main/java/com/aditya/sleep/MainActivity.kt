package com.aditya.sleep

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.aditya.sleep.databinding.ActivityMainBinding
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var permissioner: Permissioner
    private lateinit var pendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.Q)
    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    } else {
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


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        permissioner = Permissioner()
        if (!allPermissionsApproved()){
        requestPermissionss()
        }

        pendingIntent = createSleepPendingIntent(this)
        requestSleepUpdates()
    }

    private fun requestPermissionss() {
        Log.i(TAG, "requestActivityRecognitionPermission: ")
        val message =
            "For the sleep Tracking these permissions are necessary, tap Yes to grant permissions."
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissioner.requestPermissions(
                this, permissions, permissionsLauncher,
                message
            )
        }
    }


    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun requestSleepUpdates() {
        Log.i(TAG, "requestSleepUpdates---- ")
        if (permissionsApprovedspecific(Manifest.permission.ACTIVITY_RECOGNITION)) {
            Log.i(TAG, "Permission approved for activity recognition.")
            ActivityRecognition.getClient(this)
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

    @Suppress("SameParameterValue")
    private fun permissionsApprovedspecific(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    }

    fun createSleepPendingIntent(context: Context?): PendingIntent {
        val intent = Intent(context, SleepReceiver::class.java)
        Log.i(TAG, "Sleep pending intent created")
        return PendingIntent.getBroadcast(
            context,
            123,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun allPermissionsApproved(): Boolean {
        return permissions.any { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}