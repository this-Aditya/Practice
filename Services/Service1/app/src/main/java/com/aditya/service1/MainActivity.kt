package com.aditya.service1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.service1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "$binding")
        Log.i(TAG, "${binding.root}")
    }
}