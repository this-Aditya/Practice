package com.aditya.permission1

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
    }

    //When you ask for multiple permissions at an instance
        @RequiresApi(Build.VERSION_CODES.Q)
        private val permissions = arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    )

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
    private fun requestPermissions() {
        val message = "Permissions are required for proper functioning of the application. Please grant these."
        val permissionsToRequest = mutableListOf<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequest.isEmpty()) {
            // All permissions are already granted
        } else {
            val shouldShowRationale = permissionsToRequest.any {
                ActivityCompat.shouldShowRequestPermissionRationale(this, it)
            }

            if (shouldShowRationale) {
                Toast.makeText(this, "Permission not granted.", Toast.LENGTH_SHORT).show()
                showAlertDialogue(this, permissionsToRequest.toTypedArray(), permissionsLauncher, message)
            } else {
                permissionsLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }
    private fun showAlertDialogue(
        context: Activity,
        permissions: Array<String>,
        launcher: ActivityResultLauncher<Array<String>>,
        message: String
    ) {
        android.app.AlertDialog.Builder(context).apply {
            setTitle("Need permissions to function")
            setMessage(message)
            setPositiveButton("Yes") { _, _ ->
                launcher.launch(permissions) // Trigger the permission request
            }
            setNegativeButton("No") { _, _ ->
                Log.i(TAG, "Permission denied.")
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
                Log.i(TAG, "Permission granted,")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) == true -> {
                showAlertDialogue()
            }

            else -> {
                Log.i(TAG, "Permission not asked yet!")
            requestPermissionLauncher.launch(permission)
            }
        }
    }
 */