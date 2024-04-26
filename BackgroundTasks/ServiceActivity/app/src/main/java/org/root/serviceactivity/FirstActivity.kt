package org.root.serviceactivity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.root.serviceactivity.databinding.ActivityFirstBinding


private const val TAG = "FirstActivity"
class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartMain.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: FirstActivty")
    }
}