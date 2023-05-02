package com.aditya.thread2

import android.os.Handler
import android.os.Looper
import android.util.Log

private const val TAG = "ExampleLooperThread"

/**
 * Class extending thread class and creating a looper in it.
 * Looper helps in continuous execution of thread even when work of a thread is completed
 * Looper loops into the messageQueue and waits from some task.
 */
class ExampleLooperThread: Thread() {

     lateinit var handler: Handler
     var looper: Looper? = null

    override fun run() {
        Looper.prepare()
        looper = Looper.myLooper()
        handler = Handler()
        Looper.loop()

        Log.i(TAG, "End of run.")
    }
}