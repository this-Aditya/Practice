package org.root.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.root.permissions.databinding.ActivityRequestCodeBinding

private const val TAG = "RequestCodeActivity"

class RequestCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestCodeBinding


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionsToRequest: Array<String> = arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.READ_CONTACTS
        )
        binding.btnAskPermission.setOnClickListener { askForPermissions(permissionsToRequest) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permission: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults)

        when (requestCode) {
            SINGLE_PERMISSION_REQUEST_CODE -> showToastForResult("Non Rationale", permission, grantResults)
            RATIONALE_PERMISSION_REQUEST_CODE -> showToastForResult("Rationale", permission, grantResults)

        }

    }

    private fun showToastForResult(
        rationaleInfo: String,
        permission: Array<out String>,
        grantResults: IntArray
    ) {
        var i = 0
        for (perm in permission){
            if (grantResults.isNotEmpty() && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    "Permission ${perm} granted $rationaleInfo: ",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i(TAG, "Permission ${perm} granted $rationaleInfo: ")
            } else {
                Toast.makeText(this, "Permission ${perm} Not granted", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Permission ${perm} Not granted, $rationaleInfo")
            }
            i++
        }
    }

    private fun askForPermissions(permissions: Array<String>) {

        val permissionsNotGranted: Array<String> = permissions.filter { permission ->
            ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsNotGranted.isEmpty()) {
            Toast.makeText(this, "All Required permissions are Granted yey!!", Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            val rationalePermissions: Array<String> = permissionsNotGranted.filter { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
            }.toTypedArray()

            if (rationalePermissions.isNotEmpty()) {
                showRationaleDialogue(rationalePermissions)
            }
            else {
                requestPermissions(permissions, SINGLE_PERMISSION_REQUEST_CODE)
            }

//            when {
//                ContextCompat.checkSelfPermission(this, permissions) ==
//                        PackageManager.PERMISSION_GRANTED -> {
//                    Toast.makeText(this, "Permission is already granted!!", Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                ActivityCompat.shouldShowRequestPermissionRationale(this, permissions) -> {
//                    showRationaleDialogue(permissions)
//                }
//
//                else -> {
//                    requestPermissions(permissions, SINGLE_PERMISSION_REQUEST_CODE)
//                }
//            }
//        }
        }
    }
    private fun showRationaleDialogue(permissions: Array<String>) {
        AlertDialog.Builder(this)
            .setTitle("Needed permissions: ")
            .setMessage("Permissions are needed for smooth functioning of the application. Please grant these for maximum functionality")
            .setPositiveButton("Yes") { _, _ ->
                requestPermissions(permissions, RATIONALE_PERMISSION_REQUEST_CODE)
            }
            .setNegativeButton("No") { dialogue, _ ->
                dialogue.dismiss()
            }
            .create().show()
    }

    companion object {
        const val SINGLE_PERMISSION_REQUEST_CODE: Int = 347738
        const val RATIONALE_PERMISSION_REQUEST_CODE: Int = 347739
    }
}