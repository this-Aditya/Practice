package org.root.taskbackstack

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.root.taskbackstack.databinding.ActivityABinding

class ActivityA : AppCompatActivity() {

    private lateinit var binding: ActivityABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvActivityA.setOnClickListener {
            Intent(this, ActivityB::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
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

private const val TAG = "ActivityA"