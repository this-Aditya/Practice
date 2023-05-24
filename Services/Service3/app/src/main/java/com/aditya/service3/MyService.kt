package com.aditya.service3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
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
        for (i in 1 .. 10){ Log.i(TAG, "Work On String $i ${Thread.currentThread().name}")
        Thread.sleep(1000)
        if (i == 10 ){
            Toast.makeText(this," Service $i ${Thread.currentThread().name}", Toast.LENGTH_LONG).show()
        }
        }
        val dataString = intent?.getStringExtra(EXTRA_STRING)
        dataString?.let {
            Log.i(TAG, "Data String Received:- $dataString")
        }
        Log.i(TAG, "onStartCommand: Last Line ")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Service")
    }
}