package com.aditya.sleep

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.media.metrics.LogSessionId
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.aditya.sleep.SleepApplication.Companion.CHANNEL_ID
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest

private const val TAG = "SleepService"
class SleepService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        registerForSleepUpdates()
    }

    private fun registerForSleepUpdates() {
        val permission = if( Build.VERSION.SDK_INT >= 29 ) Manifest.permission.ACTIVITY_RECOGNITION
        else "com.google.android.gms.permission.ACTIVITY_RECOGNITION"
        Log.i(TAG, "registerForSleepUpdates: .....")
        if (ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "ActivityRecognition permission approved. $permission")
            ActivityRecognition.getClient(this)
                .requestSleepSegmentUpdates(
                    SleepApplication.createSleepPendingIntent(
                        this
                    ), SleepSegmentRequest.getDefaultSleepSegmentRequest()
                )
                .addOnSuccessListener {
                    Log.d(TAG, "Successfully subscribed to sleep data.")
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Exception when subscribing to sleep data: ${exception.cause}")
                }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sleep data recording....")
            .setContentText("Your sleep segment and classify data is being monitored continuously")
            .setSmallIcon(R.drawable.baseline_nights_stay_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(973,notificationBuilder)
        return START_STICKY
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 87632, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityRecognition.getClient(this)
            .removeSleepSegmentUpdates(SleepApplication.createSleepPendingIntent(this))
        Log.i(TAG, "Sleep data collection removed.")
    }
}