package com.aditya.boundservices3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.aditya.boundservices3.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var myService: MyService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel._myBinder.observe(this, Observer { binder ->
            if (binder != null) {
                myService = binder.service
                Log.i(TAG, "Connected to the service ")
            } else {
                myService = null
                Log.i(TAG, "Disconnecting from the service ")
            }
        })
    }


    override fun onResume() {
        super.onResume()
        startService()
    }

    private fun startService() {
        val intent = Intent(this, MyService::class.java)
        startService(intent)
        bindService(intent)// Not using startService and only using bindService results
        // when only activity is bound to that service
    }

    private fun bindService(intent: Intent) {
        bindService(intent, viewModel.serviceConnection, Context.BIND_AUTO_CREATE)
    }


    override fun onPause() {
        super.onPause()
        if (viewModel._myBinder.value != null) {
            unbindService(viewModel.serviceConnection)
        }
    }
}