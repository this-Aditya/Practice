package com.aditya.dynamicbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

const val TAG = "DynamicBroadcastReceive"

class DynamicBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                Log.i(TAG, "Device is Charging ")
                showToast(context, "Device is charging.")
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                Log.i(TAG, "Charger dissconnected.")
                showToast(context, "Charger dissconnected")
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val state = intent.getBooleanExtra("state", false)
                when (state) {
                    true -> {
                        Log.i(TAG, "Airplane mode is enabled ")
                        showToast(context, "Airplane mode is enabled ")
                    }

                    false -> {
                        Log.i(TAG, "Airplane mode is disabled ")
                        showToast(context, "Ariplane mode is disabled ")
                    }
                }
            }

        }
    }

    private fun showToast(context: Context?, s: String) =
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()

}