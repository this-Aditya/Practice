package com.aditya.remoteconfig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.aditya.remoteconfig.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var config: FirebaseRemoteConfig
    private val TAG = "Firebase"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        config = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        config.setConfigSettingsAsync(configSettings)

        config.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Remote config parameters updated ")
            } else {
                Log.d(TAG, "Failed to update remote config parameters.")
            }
        }


    }
    companion object CONSTANTS {
         const val SHOW_BACKGROUND_IMAGE = "SHOW_BACKGROUND_IMAGE"
    }


}