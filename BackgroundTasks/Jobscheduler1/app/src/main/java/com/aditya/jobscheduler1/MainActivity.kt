package com.aditya.jobscheduler1

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aditya.jobscheduler1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var jobScheduler: JobScheduler
    private lateinit var componentName: ComponentName
    private lateinit var jobInfo: JobInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStartJob.setOnClickListener {
            startJob()
        }
        binding.btnStopJob.setOnClickListener {
            stopJob()
        }
    }

    private fun startJob() {
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        componentName = ComponentName(this, MyJobService::class.java)
        jobInfo = JobInfo.Builder(JOB_ID,componentName)
//            .setRequiresCharging(true)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(20*16*1000)
            .build()

        val resultCode = jobScheduler.schedule(jobInfo)
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Log.i(TAG, "Job Scheduled successfully.")
        }
        else{
            Log.i(TAG, "Job Scheduling Failed.")
        }
    }

    private fun stopJob() {
        jobScheduler.cancel(JOB_ID)
    }

    companion object{
        private const val JOB_ID = 123
    }
}