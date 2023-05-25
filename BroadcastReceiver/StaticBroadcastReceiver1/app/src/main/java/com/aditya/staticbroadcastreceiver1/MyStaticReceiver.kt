package com.aditya.staticbroadcastreceiver1

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


const val TAG = "MyStaticReceiver"
class MyStaticReceiver: BroadcastReceiver() {
    val channelId = "my_channel_id"
    val title = "Timezone Changed "
    val message = "Time zone has changed for your device ."

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
             Intent.ACTION_TIMEZONE_CHANGED -> {
                showLog(context, "Timezone ")
                showToast(context, "Timezone ")
                context?.let {
                    Notificationer.showNotification(it, channelId, title, message)
                }
            }
        }
    }

    private fun showLog(context: Context?, s: String) {
        Log.i(TAG, s)
    }

    private fun showToast(context: Context?, s: String) {
        Toast.makeText(context, "$s  $context", Toast.LENGTH_LONG).show()
    }
}






