package com.aditya.remote_config2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aditya.remote_config2.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig

    private var cache: Map<String, String> = mutableMapOf()

    private val activationCompleteListener: OnSuccessListener<Boolean> = OnSuccessListener { result ->
        Log.i(TAG, "Parameters fetched and activated now, result: $result!!")
        cache = remoteConfig.getKeysByPrefix("")
            .mapNotNull { key ->
                remoteConfig.getValue(key).asString()
                    .takeUnless { it.isEmpty() }
                    ?.let { Pair(key, it) }
            }
            .toMap()

        Log.i(TAG, "Activated values: ")

        val entries = cache.entries

        entries.forEach {
            Log.i(TAG, "${it.key} -> ${it.value}")
        }

    }
    private val activationFailedListener: OnFailureListener = OnFailureListener {
        Log.i(TAG, "Parameters fetched but not activated!! ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(R.xml.remote_config_default)
        remoteConfig.fetch().addOnCompleteListener(this){task->

         if (task.isSuccessful){
             Log.i(TAG, "Remote config parameters fetched, now activating it!!")
             remoteConfig.activate().also {
                 it.addOnSuccessListener(activationCompleteListener)
                 it.addOnFailureListener(activationFailedListener)
             }
         } else {
             Log.i(TAG, "Remote config parameters failed to fetch  ")
         }
        }

//        val needUpdate = Firebase.remoteConfig.getBoolean(FIREBASE_CONTANT_1)
//        Log.i(TAG, "Permission -> $needUpdate")
//        if (needUpdate){
//            createDialogue()
//        }
//        else{
//            Log.i(TAG, "Firebase Not allowed them to get updates")
//        }





    }

    private fun createDialogue() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Firebase.remoteConfig.getString("DIALOGUE_TITLE"))
            .setMessage("Do you want to get the new beta Update. It is for some selected users only you are one of those selected If you want to get update of this beta version prior to others perss Uodate else Press Cancel. Thanks")
            .setPositiveButton("Update") { _, _ ->
                Toast.makeText(
                    this,
                    "Your acceptance is recieved. We will inform you soon.",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, "You have decided not to get this update.", Toast.LENGTH_LONG)
                    .show()
            }
        val dialog = builder.create()
        dialog.show()
    }

}