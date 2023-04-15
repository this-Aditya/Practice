package com.aditya.flows

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aditya.flows.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelfactory = MainViewModelFactory(0)
        viewModel = ViewModelProvider(this, viewModelfactory).get(MainViewModel::class.java)
        viewModel._time.observe(this, Observer { time ->
            binding.tv.text = time.toString()
            Log.i("MainActivity", "onCreate: time is $time ")
        })
        binding.btnStart.setOnClickListener {
            val duration = binding.editTextTime.text.toString().toInt()
            viewModel.setDuration(duration)
            binding.editTextTime.text.clear()
        }
        binding.btnStop.setOnClickListener {
            viewModel.setDuration(0)
        }
        binding.btnPauseResume.setOnClickListener {
            if (binding.btnPauseResume.text == "Pause") {
                viewModel.pause()
                Log.i(TAG, "FirstMethod")
                binding.btnPauseResume.text = "Resume"
            } else if (binding.btnPauseResume.text == "Resume") {
                viewModel.resume()
                Log.i(TAG, "SecondMethod")
                binding.btnPauseResume.text = "Pause"

            }
        }
    }
}