package com.aditya.intentservice

import android.app.IntentService
import android.app.Notification
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.os.SystemClock
import android.util.Log
import com.aditya.intentservice.ServiceApplication.Companion.CHANNEL_ID

private const val TAG = "MyIntentService"
class MyIntentService: IntentService("my_intent_service") {

    private lateinit var wakeLock : WakeLock

    override fun onCreate() {
        super.onCreate()

        val powerManager: PowerManager = getSystemService(POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"example: wakelock")
        wakeLock.acquire()
        Log.i(TAG, "Wakelock aqquired")

        Log.d(TAG, "onCreate: MyService")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         val notificationBuilder = Notification.Builder(this, CHANNEL_ID)
             .setContentTitle("Intent Service")
             .setContentText("Intent service Running...")
             .build()
            startForeground(1,notificationBuilder)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
        val string = intent?.getStringExtra("extra_string")
        for (i in 1..10) {
            Log.i(TAG, "$string: $i")
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service")
        wakeLock.release()
        Log.i(TAG, "Wakelock released")
    }
}