package com.aditya.sleep

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import com.aditya.sleep.models.SleepClassify
import com.aditya.sleep.models.SleepSegment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.SleepClassifyEvent
import com.google.android.gms.location.SleepSegmentEvent
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.DateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "SleepReceiver"
private const val segmentFile = "Segment.txt"
private const val classifyFile = "classify.txt"

class SleepReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive: ")
        intent?.let {
            when {
                SleepSegmentEvent.hasEvents(intent) -> {
                    val sleepSegmentEvents: List<SleepSegmentEvent> =
                        SleepSegmentEvent.extractEvents(intent)
                    val updatedSleepSegmentVersion: List<SleepSegment> = sleepSegmentEvents.map {
                        SleepSegment.toSleepSegment(it)
                    }
                    val notificationer = Notificationer()
                    context?.let {
                        notificationer.showNotification(
                            context, "123", "Sleep Segment Event",
                            "$updatedSleepSegmentVersion"
                        )
                        Log.i(TAG, "SleepSegmentEvent-> $updatedSleepSegmentVersion ")
                        saveDataToFile("$updatedSleepSegmentVersion", segmentFile)
                    }
                }

                SleepClassifyEvent.hasEvents(intent) -> {
                    val sleepClassifyEvents: List<SleepClassifyEvent> =
                        SleepClassifyEvent.extractEvents(intent)
                    val updatedClassifyEvent: List<SleepClassify> = sleepClassifyEvents.map {
                        SleepClassify.toSleepClassifyEvent(it)
                    }
                    context?.let {
                        Log.d(TAG, "SleepClassifyEvent : $updatedClassifyEvent")
                        saveDataToFile("$updatedClassifyEvent", classifyFile)
                    }
                }

                else -> {
                    Log.i(TAG, "Nothing received ")
                }
            }
        }
    }

    private fun saveDataToFile(data: String, fileName: String) {
        try {
            val publicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val file = File(publicDir, fileName)
            val fileWriter = FileWriter(file, true)
            fileWriter.append(data)
            fileWriter.append("\n")
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }



    companion object {
//        fun createSleepPendingIntent(context: Context?): PendingIntent {
//            val intent = Intent(context, SleepReceiver::class.java)
//            Log.i(TAG, "Sleep pending intent created")
//            return PendingIntent.getBroadcast(
//                context,
//                123,
//                intent,
//                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
//            )
//        }

        fun epochToDateTime(timeMilis: Long): String {
            val date = Date(timeMilis)
            val dateFormat =
                DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US)
            val formattedDateTime = dateFormat.format(date)
            return formattedDateTime
        }

        fun convertMsToHMS(milliseconds: Long): String {
            val seconds = milliseconds / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            return "$hours:${minutes % 60}:${seconds % 60}"
        }

    }
}