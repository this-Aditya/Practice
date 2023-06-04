package com.aditya.sleep.models

import com.aditya.sleep.SleepReceiver
import com.google.android.gms.location.SleepClassifyEvent

data class SleepClassify(
    val time: String, val confidence: Int, val motion: Int, val light: Int,
) {
    companion object {
        fun toSleepClassifyEvent(sleepClassifyEvent: SleepClassifyEvent): SleepClassify {
            return SleepClassify(
                SleepReceiver.epochToDateTime(sleepClassifyEvent.timestampMillis),
                sleepClassifyEvent.confidence,
                sleepClassifyEvent.motion,
                sleepClassifyEvent.light
            )
        }
    }
}