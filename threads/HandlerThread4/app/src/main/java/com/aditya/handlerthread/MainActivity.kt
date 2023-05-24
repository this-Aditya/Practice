package com.aditya.handlerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import java.util.Objects

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var btnDoWork: Button
    private lateinit var btnRemoveMessage: Button

    //Initialising default handlerThread
//    private val handlerThread = HandlerThread("handler_thread")

    //HandlerThread doesn't have handler associated with it.
//    private lateinit var threadHandler: Handler


    // Creating custom HandlerThread class to have more feature like message passing.
    val customHandlerThread = CustomHandlerThread()
    val runnable1 = MyRunnable1()
    val runnable2 = MyRunnable2()
    //just an identifier
    val token = Object()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDoWork = findViewById<Button>(R.id.btnDoWork)
        btnRemoveMessage = findViewById<Button>(R.id.btnRemoveMessages)

//        handlerThread.start()
        /**
         * HandlerThread is a java class which extends thread class.
         * It is just a thread having messageQueue and Looper attached to it.
         */
//        threadHandler = Handler(handlerThread.looper)
//     Doing the work ythrough custome Handler
        customHandlerThread.start()


        btnDoWork.setOnClickListener {
            doWork()
        }

        btnRemoveMessage.setOnClickListener {
            removeMessages()
        }
    }

    fun doWork() {

        // Anonymous runnable object have an implicit reference to the activity, so creating the static runnable.
//        threadHandler.post(object: Runnable{
//            override fun run() {
//                TODO("Not yet implemented")
//            }
//        })

//        threadHandler.postDelayed(MyRunnable2(), 2000)
//        threadHandler.post(MyRunnable1())
        customHandlerThread.customHandler().postAtTime(runnable1,token,1000)
        customHandlerThread.customHandler().post(runnable2)
//        val message = Message.obtain()
        //second way
        val message = Message.obtain(customHandlerThread.customHandler())
        message.what = EXTRA_VALUE_FIRST
        message.arg1 = 4
        message.arg2 = 32
        message.obj = " Hello Message "
        customHandlerThread.customHandler().sendEmptyMessage(2)
    //second way
        message.sendToTarget()
    }

    fun removeMessages() {
        //handler can only remove the task which it has transferred. And runnables need the same instance not newRunnable()
        customHandlerThread.customHandler().removeMessages(2)
        customHandlerThread.customHandler().removeCallbacks(runnable1,token)
    }

    override fun onDestroy() {
        super.onDestroy()
//        handlerThread.quit()
        customHandlerThread.quit()
    }

    companion object {
        class MyRunnable1 : Runnable {
            override fun run() {
                for (i in 1..4) {
                    Log.i(TAG, "MyRunnable1 $i")
                    SystemClock.sleep(1000)
                }
            }
        }

        class MyRunnable2 : Runnable {
            override fun run() {
                for (i in 1..4) {
                    Log.i(TAG, "MyRunnable2 $i")
                    SystemClock.sleep(1000)
                }
            }
        }

        const val EXTRA_VALUE_FIRST = 1
        const val EXTRA_VALUE_SECOND = 2
    }
}