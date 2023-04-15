package com.example.viewmodel1

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(name:String): ViewModel() {
    init {
        Log.i("ViewModel", "Name I got is $name: ")
    }
}