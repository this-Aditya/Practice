package com.aditya.roomcodelab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.roomcodelab.entity.Word
import com.aditya.roomcodelab.repository.WordRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VM2(private val repository: WordRepository): ViewModel() {

    fun deleteWord(word: Word){
        viewModelScope.launch {
            repository.deleteWord(word)
        }
    }

}