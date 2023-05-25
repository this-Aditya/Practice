package com.aditya.sender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "ExplicitReceiver1"
class ExplicitReceiver1: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Explicatit receiver 1 receied ", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onReceive: ")
    }
}