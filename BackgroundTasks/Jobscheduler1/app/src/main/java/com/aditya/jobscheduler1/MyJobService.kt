package com.aditya.jobscheduler1

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

const val TAG = "MyJobService"

// Job Service by default runs on UI thread.
class MyJobService : JobService() {
    private var jobCancelled: Boolean = false

    override fun onStartJob(params: JobParameters?): Boolean {

        // When the task is short and can be executed in the scope of this method, return false
        // else if the task is going long in background, return true. It tells the system to keep
        // the device awake. And we are responsible to tell when the job has been finished. To release wakelock.

        doBackgroundWork(params)
        return true
    }

    private fun doBackgroundWork(params: JobParameters?) {
        Thread {
            Runnable {
                for (i in 1..10) {
                    if (jobCancelled) {
                        return@Runnable
                    }
                    Log.i(TAG, "Doing the job in background $i")
                    Thread.sleep(1000)
                }
            }
                Log.i(TAG, "job Finished. ")
            }.start()
            jobFinished(params, false)
    }

    // Triggered when our job filed sue to some reasons.
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i(TAG, "Job cancelled before completition")
        jobCancelled = true
        return true
    }
}