package com.aditya.thread3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.aditya.thread3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val myLooperThread = MyLooperThread()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            myLooperThread.start()
            // Never Ever create Handler before starting the thread
            // and never ever start the same thread again.
        }

        // This runnable have the implicit reference to the activity as it is
        // an anonymous inner class. Which is responsible for potential memory leaks.
        // beware of that. You can use WeakReference(in next projects) or make these
        // as the inner static classes. Like below ->

        binding.btnTaskA.setOnClickListener {
            myLooperThread.myHandler.post(object : Runnable {
                override fun run() {
                    //binding Memory leak accessing class members
                    for (i in 1..5) {
                        Thread.sleep(1000)
                        Log.d(TAG, "Running On Background Thread ${Thread.currentThread().name} $i")
                    }
                }
            })
        }

        binding.btnTaskB.setOnClickListener {
            //Creater handler in main thread and then associate it with looper of another thread.
            val niceHandler = Handler(myLooperThread.myLooper)
            niceHandler.post {
                for (i in 1..5) {
                    Thread.sleep(1000)
                    Log.d(TAG, "Second Task Running On Background Thread ${Thread.currentThread().name} $i")
                }
            }
        }

        binding.btnStop.setOnClickListener {
//            myLooperThread.myHandler.looper.quit()   Below one can be also be used alternatively
            myLooperThread.myLooper.quit()
            // Don't forgot to stop the thread.
        }
    }

//    companion object{
//         class InnerRunnable: Runnable{
//             override fun run() {
//             }
//
//         }
//    } static inner class of runnable
}
