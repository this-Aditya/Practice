package com.aditya.sleep

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

private const val TAG = "SleepApplication"
class SleepApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "sleep_notification_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }


    companion object {
        const val CHANNEL_ID = "935740"

        fun createSleepPendingIntent(context: Context?): PendingIntent {
            val intent = Intent(context, SleepReceiver::class.java)
            Log.i(TAG, "Sleep pending intent created")
            return PendingIntent.getBroadcast(
                context,
                123,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }
    }
}