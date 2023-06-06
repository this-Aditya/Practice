package com.aditya.appforegroundservices

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.aditya.appforegroundservices.MainActivity.Companion.EXTRA_INPUT
import com.aditya.appforegroundservices.ServiceApplication.Companion.CHANNEL_ID

private const val TAG = "MyForegroundService"

class MyForegroundService : Service() {

    private lateinit var pendingIntent: PendingIntent

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Foreground service created.")
        val intent = Intent(this, MainActivity::class.java)
        pendingIntent =  PendingIntent.getActivity(this, 123, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val receivedText = intent.getStringExtra(EXTRA_INPUT)
        val notificationBuilder =NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Notification.")
            .setContentText(receivedText)
            .setSmallIcon(R.drawable.baseline_work_24)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notificationBuilder)
        return START_NOT_STICKY
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Foreground Service Destroyed.")
    }
}