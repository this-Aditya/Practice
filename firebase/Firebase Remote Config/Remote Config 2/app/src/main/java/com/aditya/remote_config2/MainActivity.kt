package com.aditya.remote_config2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aditya.remote_config2.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_default)
        remoteConfig.fetchAndActivate().addOnCompleteListener(this){task->

         if (task.isSuccessful){
             Log.i(TAG, "Remote config parameters updated ")
         } else {
             Log.i(TAG, "Failed to update remote config parameters.")
         }
        }

        val needUpdate = Firebase.remoteConfig.getBoolean(FIREBASE_CONTANT_1)
        Log.i(TAG, "Permission -> $needUpdate")
        if (needUpdate){
            createDialogue()
        }
        else{
            Log.i(TAG, "Firebase Not allowed them to get updates")
        }





    }

    private fun createDialogue() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Firebase.remoteConfig.getString("DIALOGUE_TITLE"))
            .setMessage("Do you want to get the new beta Update. It is for some selected users only you are one of those selected If you want to get update of this beta version prior to others perss Uodate else Press Cancel. Thanks")
            .setPositiveButton("Update") { dialogue, which ->
                Toast.makeText(
                    this,
                    "Your acceptance is recieved. We will inform you soon.",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancel") { dialogue, which ->
                Toast.makeText(this, "You have decided not to get this update.", Toast.LENGTH_LONG)
                    .show()
            }
        val dialog = builder.create()
        dialog.show()
    }

    companion object{
        private const val FIREBASE_CONTANT_1 = "SHOW_UPDATE_DIALOGUE"
    }
}