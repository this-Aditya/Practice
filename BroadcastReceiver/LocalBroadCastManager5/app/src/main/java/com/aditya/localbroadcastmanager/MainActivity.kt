package com.aditya.localbroadcastmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.aditya.localbroadcastmanager.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var localBroadcastManager: LocalBroadcastManager

    val localReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i(TAG, "onReceive: ")
            if (intent?.action == DEMO_ACTION) {
                val extraString = intent.getStringExtra(DEMO_EXTRA_STRING)
                Toast.makeText(
                    context,
                    "Receiver received, Extra String -> $extraString",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i(TAG, "Receiver received, Extra String -> $extraString")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localBroadcastManager = LocalBroadcastManager.getInstance(this)

        IntentFilter(DEMO_ACTION).also {
            localBroadcastManager.registerReceiver(localReceiver, it)
        }

        binding.button.setOnClickListener {
            sendLocalBroadcast()
        }
    }

    private fun sendLocalBroadcast() {
        val intent = Intent(DEMO_ACTION)
        intent.putExtra(DEMO_EXTRA_STRING, "Extra demo string")
        localBroadcastManager.sendBroadcast(intent)
    }

    companion object {
        const val DEMO_ACTION = "com.aditya.localbroadcastmanager.DEMO_ACTION"
        const val DEMO_EXTRA_STRING = "com.aditya.localbroadcastmanager.DEMO_EXTRA_STRING"
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(localReceiver)
    }
}


