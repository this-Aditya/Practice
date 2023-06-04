package com.aditya.contexttoast1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.aditya.contexttoast1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {
            for (i in 1..10) {
                delay(1000)
            }
            Log.i(TAG, "LifecycleScope Method : ")
            Toast.makeText(this@MainActivity, "${this@MainActivity} Activity Context of First Activity", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "LifecycleScope Method : 2")
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}