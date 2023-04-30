package com.aditya.roomcodelab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.aditya.roomcodelab.databinding.ActivityNewWordBinding
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.viewmodel.TAG
import com.aditya.roomcodelab.viewmodel.WordViewModel
import com.google.android.material.snackbar.Snackbar

class NewWordActivity : AppCompatActivity() {
   private lateinit var binding: ActivityNewWordBinding
//   private lateinit var viewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel = MainActivity().viewModel
        binding.btnSave.setOnClickListener {
            if (binding.etNewWord.text.trim().isBlank()){
                Toast.makeText(this,"This field can't be empty.",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val word = binding.etNewWord.text.toString()
            val intent = Intent()
            Log.i(TAG, "new Word Sent -> $word")
            intent.putExtra("NEW_WORD_RECEIVED",Word(0,word))
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Second")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Second")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mi_delete){
           val wordToDelete = intent.getSerializableExtra("EXTRA_WORD_DELETE")
            Log.i(TAG, "Word to Delete $wordToDelete")
        }
        return super.onOptionsItemSelected(item)
    }
    companion object{
//        const val NEW_WORD = "NEW_WORD"
//        const val RESULT_SUCCESS = 200
    }


}