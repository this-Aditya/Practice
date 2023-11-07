package org.root.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.root.permissions.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun requestPermissions() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                    == PackageManager.PERMISSION_GRANTED -> {
                binding.rootView.background = ColorDrawable(Color.CYAN)
                Toast.makeText(this, "Permission Granted, yey!", Toast.LENGTH_SHORT).show()
                Thread.sleep(2000)
                binding.rootView.background = ColorDrawable(Color.WHITE)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACTIVITY_RECOGNITION) -> {
                Toast.makeText(this, "Show rationale!!", Toast.LENGTH_SHORT).show()
                showRationale()
            }

            else -> {
                //TOD("NOT REQUESTED THE PERMISSIONS YET!! ASK THEM FOR FIRST TIME")
            }

        }
    }

    private fun showRationale() {
        AlertDialog.Builder(this)
            .setTitle("Permission Needed: ")
            .setMessage("Permissions Listed are needed for the application to work smoothly. Please grant these..")
            .setPositiveButton("Okay") { _, _ ->
                Toast.makeText(this, "Requesting The permissions!!", Toast.LENGTH_SHORT).show()
            // TOD: requesting the launcher here
            }
            .setNegativeButton("No") { dialogue, _ ->
                dialogue.dismiss()
            }.create().show()
    }
}






