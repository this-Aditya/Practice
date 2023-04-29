package com.aditya.roomcodelab.viewmodel

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insertWord(word: Word){
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