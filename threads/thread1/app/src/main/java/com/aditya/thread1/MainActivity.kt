package com.aditya.thread1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aditya.thread1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Creating handler to pass messages and runnables between threads.
    private val mHandler: Handler = Handler()
    private var isStopped: Boolean = false
    private lateinit var runnable: NewRunnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnstart.setOnClickListener {
            isStopped = false
            val secondsString = binding.etSeconds.text.toString()
            val seconds = secondsString.toIntOrNull() ?: 10
             runnable = NewRunnable
            runnable.seconds = seconds
            runnable.isStopped = isStopped
            Thread(runnable).start()
        }

        binding.btnstop.setOnClickListener {
            isStopped = true
            runnable.isStopped = true
        }
    }

//    inner class NewRunnable(val seconds: Int) : Runnable {
//        override fun run() {
//            Log.i(TAG, "Running in thread ${Thread.currentThread().name}")
//            for (i in 1..seconds) {
//                if (isStopped) return
//                if (i == 5) {
    //This Won't work as UI components can be updated on UI thread only
    //*  binding.btnstart.text = "50%" */
    //First approach -> Using mHandler associated with UI thread
    /** mHandler.post(object : Runnable{
    override fun run() {
    binding.btnstart.text = "50%"
    }
    })*/

    //Second approach -> Using Looper associated with mainHandler and
    // using it with Handler initialised in BACKGROUND thread
    /*val threadHandler = Handler(Looper.getMainLooper())
    threadHandler.post(object : Runnable{
        override fun run() {
            binding.btnstart.text = "50%"
        }

    })*/


    //Third Approach -> Using a Predefined function
    //runOnUiThread

//                    runOnUiThread(object : Runnable{
//                        override fun run() {
//                            binding.btnstart.text = "50%"
//                        }
//                    })
//
//                }
//
//                    Thread.sleep(1000)
//                    Log.i(TAG, "Thread time $i")
//            }
//        }
//    }

/**
 * Second way through which runnable should be implemented
 * But This is not recommended.
 *
 */
//    object NewRunnable : Runnable {
//        var seconds: Int = 0
//        var isStopped: Boolean = false
//        override fun run() {
//          for (i in 1..seconds){
//              if (isStopped) return
//              if (i==5){
//                  updateUI()
//              }
//              Thread.sleep(1000)
//              Log.i(TAG, "run: $i, Thread: ${Thread.currentThread().name}")
//          }
//        }
//
//        private fun updateUI() {
//            Log.i(TAG, "updateUI")
//        }
//
//    }
//}
