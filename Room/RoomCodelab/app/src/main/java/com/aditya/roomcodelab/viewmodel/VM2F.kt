package com.aditya.roomcodelab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.roomcodelab.repository.WordRepository

class VM2F(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VM2(repository) as T
    }
}