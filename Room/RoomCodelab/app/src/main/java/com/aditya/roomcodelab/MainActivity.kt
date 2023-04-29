package com.aditya.roomcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.roomcodelab.adapter.WordsAdapter
import com.aditya.roomcodelab.databinding.ActivityMainBinding
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.repository.WordRepository
import com.aditya.roomcodelab.viewmodel.WordViewModel
import com.aditya.roomcodelab.viewmodel.WordViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: WordViewModel
    var words = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository: WordRepository = (application as WordApplication).wordRepository
        val viewModelFactory = WordViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(WordViewModel::class.java)
        createAdapter()

    }

    private fun createAdapter() {
        binding.recyclerView.adapter = WordsAdapter(this,words)
    }
}