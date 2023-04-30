package com.aditya.roomcodelab.viewmodel

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.repository.WordRepository
import kotlinx.coroutines.launch

 const val TAG = "WordViewModel"
class WordViewModel(private val repository: WordRepository): ViewModel() {

    var allWords: LiveData<List<Word>>
    init {
         allWords = repository.allWords.asLiveData()
        allWords.value?.forEach {
            Log.i(TAG, "Total-> $it: ")
        }
    }


    fun insertWord(word: Word){
        Log.i(TAG, "insertWord: ")
        viewModelScope.launch {
            repository.insertWord(word)
        }
    }

    fun deleteAllWords(){
        viewModelScope.launch {
            repository.deleteAllWords()
        }
    }
}