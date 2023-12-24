package com.aditya.avrotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.radarcns.passive.google.GooglePlacesInfo

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val googlePlacesInfo = GooglePlacesInfo()
        val types = googlePlacesInfo.types
        Log.w(TAG, "Types -> $types", )
    }
}