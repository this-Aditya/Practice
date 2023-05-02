package com.example.coroutines

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val job =GlobalScope.launch(Dispatchers.Main){
//            val time = measureTimeMillis {
//            val job1 = async { doCall1()}
//            val job2 = async { doCall2()}
//                job1.join()
//                job2.join()
//        }
//
//           Log.i(TAG, "Time taken is $time")
//
//       }
//
//    }
//
//    private suspend fun doCall2() {
//        delay(3000)
//        Log.i(TAG, "doCall2: ${Thread.currentThread().name}")
//    }
//
//    private suspend fun doCall1() {
//        delay(3000)
//        Log.i(TAG, "doCall1: ${Thread.currentThread().name}")
//    }

//        lifecycleScope.launch{
//            val response1 = async{getAnswer1()}
//            val response2 = async{getAnswer2()}
//            Log.i(TAG, response1.await())
//            Log.i(TAG, response2.await())
//        }
//        Log.i(TAG, "Before The Coroutine Block ")
//        lifecycleScope.launch(Dispatchers.Main) {
//            getAnswer1()
//            Log.i(TAG, "First Answer got!!")
//        }
//        Log.i(TAG, "After  the Coroutine")

//        lifecycleScope.launch {
//            var i =0
//            while (isActive){
//                Log.i(TAG, "onCreate: $i")
//                i++
//            }
//        }
        Log.i(TAG, "onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    private suspend fun getAnswer1() {
        delay(2000)
        val context = application.getApplicationContext()
        Log.i(TAG, "This $this  Context $context, third $applicationContext")
    }
    

    private suspend fun getAnswer2(): String {
        delay(10000)
        return "Answer 2"
    }
}