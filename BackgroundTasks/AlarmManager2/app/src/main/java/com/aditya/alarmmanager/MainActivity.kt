package com.aditya.alarmmanager

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.activity.result.contract.ActivityResultContracts
import com.aditya.alarmmanager.databinding.ActivityMainBinding
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    val registerForResultPermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
    {isGranted->
        when (isGranted){
            true-> Log.i(TAG, "granted : ")
            else -> Log.i(TAG, "Not granted : ")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSetAlarm.setOnClickListener {
            registerForResultPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            val timePicker = TimePickerFragment()
            timePicker.show(supportFragmentManager, "time pickerr")
        }
        binding.btnCancelAlarm.setOnClickListener {
            cancelAlarm()
        }
    }

    private fun cancelAlarm() {
        alarmManager.cancel(pendingIntent)
        binding.textView.text = "Alarm Cancelled"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val cl = Calendar.getInstance()
        cl.set(Calendar.HOUR_OF_DAY, hourOfDay)
        cl.set(Calendar.MINUTE, minute)
        cl.set(Calendar.SECOND, 0)
        updateTV(cl)
        setAlarm(cl)
    }

    private fun updateTV(cl: Calendar) {
        binding.textView.text =
            "Alarm set for-> ${DateFormat.getTimeInstance(DateFormat.SHORT).format(cl.time)}"
    }

    private fun setAlarm(cl: Calendar) {
         alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
         val intent = Intent(this, Alertreceiver::class.java)
          pendingIntent = PendingIntent.getBroadcast(this, 1, intent,
         PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cl.timeInMillis, pendingIntent )
    }
}