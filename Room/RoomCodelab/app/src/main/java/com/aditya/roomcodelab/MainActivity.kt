package com.aditya.roomcodelab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.roomcodelab.adapter.WordsAdapter
import com.aditya.roomcodelab.databinding.ActivityMainBinding
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.repository.WordRepository
import com.aditya.roomcodelab.viewmodel.TAG
import com.aditya.roomcodelab.viewmodel.WordViewModel
import com.aditya.roomcodelab.viewmodel.WordViewModelFactory

class MainActivity : AppCompatActivity() {
    val newName = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ response->
      if (response.resultCode == Activity.RESULT_OK){
          val newWord: Word = response.data?.getSerializableExtra("NEW_WORD_RECEIVED") as Word
          Log.i(TAG, "New Word Received : ${newWord.word} ")
          viewModel.insertWord(newWord)
          adapter.notifyDataSetChanged()
      }
    }

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: WordViewModel
    lateinit var adapter: WordsAdapter
    var words = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository: WordRepository = (application as WordApplication).wordRepository
        val viewModelFactory = WordViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(WordViewModel::class.java)
        createAdapter()
        viewModel.allWords.observe(this, Observer { wordss->
            Log.i(TAG, "In Observer -> $wordss")
            words.clear()
            words.addAll(wordss)
            adapter.notifyDataSetChanged()
        })
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            newName.launch(intent)
        }

        binding.floatingActionButton2.setOnClickListener {
            viewModel.deleteAllWords()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: First")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: First")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: First")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: First")
    }

    private fun createAdapter() {
        adapter = WordsAdapter(this,words,object: WordsAdapter.ItemClicked{
            override fun onitemClicked(positon: Int) {
                val intent2 = Intent(this@MainActivity,NewWordActivity::class.java)
                intent2.putExtra("EXTRA_WORD_DELETE",words[positon])
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}