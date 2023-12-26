package org.root.taskbackstack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.root.taskbackstack.databinding.ActivityCBinding

class ActivityC : AppCompatActivity() {
    private lateinit var binding: ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvActivityC.setOnClickListener {
            Intent(this, ActivityD::class.java).apply {
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

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}

private const val TAG = "ActivityC"