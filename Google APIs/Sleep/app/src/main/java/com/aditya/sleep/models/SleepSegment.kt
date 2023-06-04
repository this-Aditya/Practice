package com.aditya.sleep.models

import com.aditya.sleep.SleepReceiver
import com.google.android.gms.location.SleepSegmentEvent
import java.util.ServiceLoader

data class SleepSegment(
    val startTime: String, val endTime: String, val duration: String, val status: SleepStatus
) {
    companion object {
        fun toSleepSegment(sleepSegmentEvent: SleepSegmentEvent): SleepSegment {
            return SleepSegment(
                SleepReceiver.epochToDateTime(sleepSegmentEvent.startTimeMillis),
                SleepReceiver.epochToDateTime(sleepSegmentEvent.endTimeMillis),
                SleepReceiver.convertMsToHMS(sleepSegmentEvent.segmentDurationMillis),
                sleepSegmentEvent.status.toSleepStatus()
            )
        }

        fun Int.toSleepStatus(): SleepStatus = when (this) {
            0 -> SleepStatus.STATUS_SUCCESSFUL
            1 -> SleepStatus.STATUS_MISSING_DATA
            2 -> SleepStatus.STATUS_NOT_DETECTED
            else -> SleepStatus.UNKNOWN
        }
    }
}