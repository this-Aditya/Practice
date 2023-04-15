package com.example.coroutines

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

        lifecycleScope.launch{
            val response1 = async{getAnswer1()}
            val response2 = async{getAnswer2()}
            Log.i(TAG, response1.await())
            Log.i(TAG, response2.await())
        }
    }

    private suspend fun getAnswer1(): String {
        delay(2000)
        return  "Answer 1"
    }
    private suspend fun getAnswer2(): String {
        delay(10000)
        return "Answer 2"
    }
}