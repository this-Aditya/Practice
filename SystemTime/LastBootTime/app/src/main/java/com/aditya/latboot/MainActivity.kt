package com.aditya.latboot

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lastBootMillis = getLastBootTime()
        val  lastBootInfo = epochToDateTime(lastBootMillis)

        Log.i("BootInfo", "Last Boot time ->$lastBootInfo")

    }

    fun getLastBootTime(): Long {
        val uptimeMillis = SystemClock.elapsedRealtime()
        val currentTimeMillis = System.currentTimeMillis()
        return currentTimeMillis - uptimeMillis
    }

    fun epochToDateTime(timeMilis: Long): String {
        val date = Date(timeMilis)
        val dateFormat =
            DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US)
        return dateFormat.format(date)
    }

}