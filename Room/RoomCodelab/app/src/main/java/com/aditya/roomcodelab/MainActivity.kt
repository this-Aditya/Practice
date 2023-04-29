package com.aditya.roomcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.roomcodelab.adapter.WordsAdapter
import com.aditya.roomcodelab.databinding.ActivityMainBinding
import com.aditya.roomcodelab.entity.Word

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var words = mutableListOf<Word>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         createAdapter()
    }

    private fun createAdapter() {
        binding.recyclerView.adapter = WordsAdapter(this,words)
    }
}