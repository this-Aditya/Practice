package com.aditya.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "ExplicitbroadcastReceiv"
class ExplicitbroadcastReceiver2: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive: $context")
        Toast.makeText(context, "Explicati broadast from other application", Toast.LENGTH_SHORT).show()
    }
}