package com.aditya.permission1

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aditya.permission1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnForeground.setOnClickListener {
            requestPermissions()
        }
    }

    //When you ask for multiple permissions at an instance
        private val permissions = arrayOf(
//            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

    private val permissionsLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            handlePermissionsResult(permissions)
        }

    private fun handlePermissionsResult(permissions: Map<String, Boolean>) {
        for ((permission, isGranted) in permissions) {
            when (isGranted) {
                true -> Log.w(TAG, "Permission Granted: $permission")
                false -> Log.w(TAG, "Permission denied: $permission")
            }
        }
    }


    private fun requestPermissions() {
        val message = "Permissions are required for proper functioning of the application. Please grant these permissions."
        val permissionsToRequest = mutableListOf<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w(TAG, "permission not granted yet for $permission")
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequest.isEmpty()) {
                // All permissions are already granted
            Log.w(TAG, "All permissions are granted.", )
                } else {
            val shouldShowRationale = permissionsToRequest.any {
                ActivityCompat.shouldShowRequestPermissionRationale(this, it)
            }

            if (shouldShowRationale) {
                Toast.makeText(this, "Permission not granted, showing rationale", Toast.LENGTH_SHORT).show()
                message.showAlertDialogue(
                    this,
                    permissionsToRequest.toTypedArray(),
                    permissionsLauncher
                )
            } else {
                Log.w(TAG, "requesting permission $permissionsToRequest, first time.", )
                permissionsLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }
    private fun String.showAlertDialogue(
        context: Activity,
        permissions: Array<String>,
        launcher: ActivityResultLauncher<Array<String>>
    ) {
        android.app.AlertDialog.Builder(context).apply {
            setTitle("Need permissions to function")
            setMessage(this@showAlertDialogue)
            setPositiveButton("Yes") { _, _ ->
                launcher.launch(permissions) // Trigger the permission request
                Log.w(TAG, "Triggered the permission requests.")
            }
            setNegativeButton("No") { _, _ ->
                Log.w(TAG, "Permission denied.")
            }
        }.create().show()
    }

}
// When You need a single permission

/*
private val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Manifest.permission.ACTIVITY_RECOGNITION
    } else {
        " "
    }

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> Log.d(TAG, "Permission granted.")
                false -> Log.d(TAG, "Permission denied.")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()
    }

    private fun showAlertDialogue() {
        AlertDialog.Builder(this)
            .setTitle("Permission needed")
            .setMessage("Permission is needed for the smooth performance of this app. Tap on yes to grant.")
            .setPositiveButton("Yes") {_,_ ->
                requestPermissionLauncher.launch(permission)
            }.create().show()

    }

    private fun requestPermission() {
        when {
            ActivityCompat.checkSelfPermission(
                this, permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.w(TAG, "Permission granted,")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) == true -> {
                showAlertDialogue()
            }

            else -> {
                Log.w(TAG, "Permission not asked yet!")
            requestPermissionLauncher.launch(permission)
            }
        }
    }
 */