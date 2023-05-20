package com.aditya.helloservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

private const val TAG = "MyIntentService"

class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MyIntentService
        var isRunning = false
        fun stopService() {
            isRunning = false
            instance.stopSelf()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        var i = 0
        while (isRunning) {
            i++
            Log.i(TAG, "Service in running $i in thread ${Thread.currentThread().name}")
            Thread.sleep(1000)
        }
    }
}