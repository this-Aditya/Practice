package com.aditya.alarmmanager

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cl = Calendar.getInstance()
        val hour = cl.get(Calendar.HOUR)
        val minute = cl.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity, activity as (TimePickerDialog.OnTimeSetListener), hour, minute,
            DateFormat.is24HourFormat(activity)
        )
    }
}