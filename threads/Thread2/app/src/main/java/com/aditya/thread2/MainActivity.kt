package com.aditya.thread2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.aditya.thread2.databinding.ActivityMainBinding
import java.lang.Thread

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val looperThread = ExampleLooperThread()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnStart.setOnClickListener {
//            start()
//        }
//
//        binding.btnTaskA.setOnClickListener {
//            TaskA()
//        }
//
//        binding.btnStop.setOnClickListener {
//            stop()
//        }
//    }

    class MyHandlerThread(name: String, priority: Int) : HandlerThread(name, priority) {
        override fun onLooperPrepared() {
            Log.i(TAG, "onLooperPrepared: ")
        }

        override fun run() {
            super.run()
            Log.i(TAG, "run: completed!!")
        }
        
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "Before starting the program!!")
        var firstHandler: Handler? = null
        val firstThread: HandlerThread = MyHandlerThread("Handler_Thread", THREAD_PRIORITY_BACKGROUND ).
        apply {
            Log.i(TAG, "Thread started!!")
        }

        binding.btnStart.setOnClickListener {
            firstThread.start()
            firstHandler = Handler(firstThread.looper)
            Log.i(TAG, "Thread started: ${Thread.currentThread().name}")
        }
        binding.btnTaskA.setOnClickListener {
            Log.i(TAG, "Task 1 of handler started! In thread ${Thread.currentThread().name}")
            val runnable: Runnable = Runnable {
                Log.i(TAG, "Task 1 of handler completed! In thread ${Thread.currentThread().name}")
            }
            firstHandler?.post(runnable)
            Log.i(TAG, "Done Task A")
        }

        binding.btnTaskB.setOnClickListener {
            Log.i(TAG, "Task 2 of handler started! In thread ${Thread.currentThread().name}")
            val result = doTaskB(firstHandler)
            Log.i(TAG, result)
            Log.i(TAG, "Done from Task B!!")
            Log.i(TAG, "No work left now")
            showAlertDialogue()
        }
    }

    private fun doTaskB(firstHandler: Handler?): String {
        val runnable = Runnable {
            Log.i(TAG, "Task 2 of Handler completed! In thread ${Thread.currentThread().name}")
        }
        val x = firstHandler?.postDelayed(runnable, 5000)

        return "Yey! Work done!!!!"
    }

    private fun showAlertDialogue() {
        AlertDialog.Builder(this)
            .setTitle("Alert Dialogue")
            .setMessage("Nothing just Alert Dialogue for checking thread functioning")
            .setPositiveButton("yes") { dialogue, _ ->
                Log.i(TAG, "Dismissing the dialogue!!")
                dialogue.dismiss()
            }.show()
    }


    fun start() {
        try {
            looperThread.start()
        } catch (e:java.lang.Exception){
            Log.i(TAG, "${e}")
        }
    }

    fun stop() {
        looperThread.looper?.quitSafely()
    }

    fun TaskA() {
        looperThread.handler.post(
            object : Runnable{
                override fun run() {
                    for (i in 1..5) {
                        Log.i(TAG, "run: $i, Thread: ${Thread.currentThread().name}")
                        Thread.sleep(1000)
                    }
                }
            }
        )
    }
//
//    fun TaskB() {
//
//    }

    inner class ExampleRunnable: Runnable{
        override fun run() {
            for (i in 1..10){

            }
        }

    }
}