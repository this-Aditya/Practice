package com.aditya.boundservices3

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log

private const val TAG = "MyService"

class MyService : Service() {
    private lateinit var handler: Handler
    private var currentProgress: Int = 0
    private var maxValue: Int = 5000
    private var isPaused: Boolean = true
    private val myBinder: IBinder = MyBinder()

    val _isPaused
        get() = isPaused
    val _progress
        get() = currentProgress
    val _maxvalue
        get() = maxValue

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
    }

    override fun onBind(intent: Intent?): IBinder {
        return myBinder
    }

    inner class MyBinder : Binder() {
        val service
            get() = this@MyService
    }

    fun startPretendLongRunningTask() {
        isPaused = false
        val runnable = object : Runnable {
            override fun run() {
                if (currentProgress >= maxValue || isPaused) {
                    Log.d(TAG, "run: Removing callbacks.")
                    handler.removeCallbacks(this)
                    pausePretendLongRunningTask()
                } else {
                    Log.d(TAG, "run: Progress: $currentProgress")
                    currentProgress += 100
                    handler.postDelayed(this, 100)
                }
            }
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun pausePretendLongRunningTask() {
        isPaused = true
    }

    private fun unPausePretendLongRunningTasks() {
        isPaused = false
        startPretendLongRunningTask()
    }

    fun resetTask() {
        currentProgress = 0
        pausePretendLongRunningTask()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }
}