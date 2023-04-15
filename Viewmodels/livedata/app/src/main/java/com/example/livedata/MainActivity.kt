package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.btnStart.setOnClickListener {
            viewModel.startTimer()
        }
        viewModel.second().apply {
            this.observe(this@MainActivity, Observer {
                binding.textview.text = it.toString()
            })
        }
        viewModel.isFinsihed.observe(this, Observer {
            Toast.makeText(this,"Finished ",Toast.LENGTH_LONG).show()
        })
    }
}