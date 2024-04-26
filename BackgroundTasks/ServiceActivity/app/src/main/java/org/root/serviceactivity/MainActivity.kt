package org.root.serviceactivity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import org.root.serviceactivity.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var binder: ActivityService.ServiceBinder? = null
    private val serviceConnection = MyServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStartService.setOnClickListener {
            Intent(this, ActivityService::class.java).apply {
                Log.i(TAG, "Starting service")
                bindService(this, serviceConnection, BIND_AUTO_CREATE)
            }
        }

        binding.btnStartActivity.setOnClickListener {
            if (binder != null) {
                (binder as ActivityService.ServiceBinder).apply {
                    startSecondActivity()
                }
            }
            else {
                Log.i(TAG, "Binder Null")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Main Activity")
    }

    inner class MyServiceConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(TAG, "onServiceConnected: ")
            binder = service as ActivityService.ServiceBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "onServiceDisconnected: ")
            binder = null
        }
    }
}

