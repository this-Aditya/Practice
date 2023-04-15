package com.aditya.thread1

import android.util.Log

private const val TAG = "NewThread"
class NewThread(val seconds: Int) : Thread() {

    override fun run() {
        for (i in 1..seconds) {
            Log.i(TAG, "onCreate: $i")
            Thread.sleep(1000)
        }
    }
}