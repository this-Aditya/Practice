package com.aditya.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

const val TAG = "Alertreceiver"
class Alertreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive: ")
        val notification = NotificationHandler()
        context?.let {
            Log.i(TAG, "onReceive: 2")
            notification.showNotification(
                context, "Alarm channel id", "Alarm is Live ", "Hey, " +
                        "Your set up alarm is live now. Act whatever you want to do."
            )
        }
    }
}