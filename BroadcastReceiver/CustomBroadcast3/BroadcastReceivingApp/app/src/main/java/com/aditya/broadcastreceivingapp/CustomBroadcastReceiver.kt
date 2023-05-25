package com.aditya.broadcastreceivingapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.aditya.broadcastreceivingapp.MainActivity.Companion.CUSTOM_ACTION
import com.aditya.broadcastreceivingapp.MainActivity.Companion.CUSTOM_EXTRA_STRING

private val TAG = "CustomBroadcastReceiver"

class CustomBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive: Broadcast Custom")
        when (intent?.action) {
            CUSTOM_ACTION -> {
                val extraString = intent.getStringExtra(CUSTOM_EXTRA_STRING)
                Log.i(TAG, "onReceive: Second Check $extraString")
                Toast.makeText(context, " Custom Broadcast Received extras -> $extraString", Toast.LENGTH_LONG).show()
                Log.i(TAG, "onReceive: Third Check")
            }
        }
    }

    private fun showToast(context: Context?, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}