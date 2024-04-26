package org.root.serviceactivity

import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_TASK_ON_HOME
import android.os.Binder
import android.os.IBinder
import android.util.Log

private const val TAG = "ActivityService"
class ActivityService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        return ServiceBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    fun startSecondActivity() {
        Log.i(TAG, "Intent: (Service-> $this, SecondActvity: ${SecondActivity::class.java}")
        Intent(this, SecondActivity::class.java).apply {
            Log.i(TAG, "Method: startSecondActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_TASK_ON_HOME or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Service Destroyed")
    }

  inner class ServiceBinder : Binder() {
        fun startSecondActivity() = this@ActivityService.startSecondActivity()
  }
}