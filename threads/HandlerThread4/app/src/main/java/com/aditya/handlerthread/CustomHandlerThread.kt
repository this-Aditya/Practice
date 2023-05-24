package com.aditya.handlerthread

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.Process
import android.os.SystemClock
import android.util.Log
import com.aditya.handlerthread.MainActivity.Companion.EXTRA_VALUE_FIRST
import com.aditya.handlerthread.MainActivity.Companion.EXTRA_VALUE_SECOND

class CustomHandlerThread :
    HandlerThread("custom_handler_thread", Process.THREAD_PRIORITY_DEFAULT) {
    //We can't initialise it in init, this will happen in main thread only.
    // We can just do in run() as this method start background thread
    private lateinit var handler: Handler

    fun customHandler() = handler

    // method in run() in superclass best for initialising handler in background thread
    override fun onLooperPrepared() {
        //one way ->
//        handler = object : Handler(){
//            override fun handleMessage(msg: Message) {
//            }
//        }

        // second way ->
        handler = MyCustomHandler()
        //no need to pass looper automatically in background thread.
    }

    companion object {
        class MyCustomHandler() : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    EXTRA_VALUE_FIRST -> {
                        Log.i(
                            TAG,
                            "handleMessage: arg1 ->  ${msg.arg1}, arg2 -> ${msg.arg2}, object -> ${msg.obj}"
                        )
                        for (i in 1..4) {
                            Log.i(TAG, "Handling message in custom handler $i")
                            SystemClock.sleep(1000)
                        }
                    }
                    EXTRA_VALUE_SECOND -> {
                        Log.i(TAG, "Extra value second ")
                    }
                }

            }
        }
    }
}