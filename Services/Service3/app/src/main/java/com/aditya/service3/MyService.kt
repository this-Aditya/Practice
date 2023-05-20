package com.aditya.service3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.aditya.service3.MainActivity.Companion.EXTRA_STRING

private const val TAG = "MyService"

class MyService : Service() {

    init {
        Log.i(TAG, "Service Started ")
    }

    override fun onCreate() {
        Log.i(TAG, "onCreate: Service Class")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service Intent Started")
        val dataString = intent?.getStringExtra(EXTRA_STRING)
        dataString?.let {
            Log.i(TAG, "Data String Received:- $dataString")
        }
        return START_STICKY
    }
}