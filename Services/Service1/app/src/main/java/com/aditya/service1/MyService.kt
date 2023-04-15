package com.aditya.service1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

private const val TAG = "MyService"

class MyService : Service() {
    private val mBinder: IBinder = MyBinder()
    private lateinit var mHandler: Handler
    private var mProgress: Int = 0
    private var mMaxValue: Int = 5000
    private var misPaused: Boolean = true

    val _isPaused: Boolean
        get() = misPaused

    val _progress: Int
        get() = mProgress

    val _maxValue: Int
        get() = mMaxValue

    override fun onCreate() {
        super.onCreate()
        mHandler = Handler()
        mProgress = 0
        misPaused = true
        mMaxValue = 5000 }

    override fun onBind(intent: Intent?): IBinder = mBinder

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    fun startPretendingLongRunningTask() {
        val runnable = object : Runnable {
            override fun run() {
                if (mProgress >= mMaxValue || misPaused) {
                    Log.i(TAG, "run: Removing callbacks.")
                    mHandler.removeCallbacks(this)
                    pausePretendLongRunnningTask()
                } else {
                    mProgress += 100
                    mHandler.postDelayed(this, 100)
                }
            }

        }

    }

    private fun pausePretendLongRunnningTask() {
        misPaused = true
    }

    private fun unPausePretendLongRunningTask() {
        misPaused = false
        startPretendingLongRunningTask()
    }

    private fun resetTask(){
        mProgress = 0
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.i(TAG, "onTaskRemoved Called.")
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy Called.")
    }
}