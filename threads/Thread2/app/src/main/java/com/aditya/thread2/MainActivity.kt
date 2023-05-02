package com.aditya.thread2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.thread2.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private val looperThread = ExampleLooperThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            start()
        }

//        binding.btnTaskA.setOnClickListener {
//            TaskA()
//        }
//
//        binding.btnStop.setOnClickListener {
//            stop()
//        }
    }


//    fun start() {
//        try {
//            looperThread.start()
//        } catch (e:java.lang.Exception){
//            Log.i(TAG, "${e}")
//        }
//    }
//
//    fun stop() {
//        looperThread.looper?.quitSafely()
//    }
//
//    fun TaskA() {
//        looperThread.handler.post(
//            object : Runnable{
//                override fun run() {
//                    for (i in 1..5) {
//                        Log.i(TAG, "run: $i, Thread: ${Thread.currentThread().name}")
//                        Thread.sleep(1000)
//                    }
//                }
//            }
//        )
//    }
//
//    fun TaskB() {
//
//    }

    fun start(){

    }

    inner class ExampleRunnable: Runnable{
        override fun run() {
            for (i in 1..10){

            }
        }

    }
}