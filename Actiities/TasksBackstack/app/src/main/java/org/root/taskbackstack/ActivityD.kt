package org.root.taskbackstack

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.root.taskbackstack.databinding.ActivityDBinding

private const val TAG = "ActivityD"
class ActivityD : AppCompatActivity() {
    private lateinit var binding: ActivityDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvActivityD.setOnClickListener {
            Intent(this, ActivityE::class.java).apply {
                startActivity(this)
            }
        }

        binding.tvActivityDT.setOnClickListener {
            Intent(this, ActivityB::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
                addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
}