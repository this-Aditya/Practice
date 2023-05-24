package com.aditya.thread3

import android.os.Handler
import android.os.Looper

const val TAG = "MyLooperThread"

class MyLooperThread : Thread() {
    lateinit var myHandler: Handler
    lateinit var myLooper: Looper

    override fun run() {
        Looper.prepare()
        myLooper = Looper.myLooper()!!
        myHandler = Handler()
        Looper.loop()
    }
}