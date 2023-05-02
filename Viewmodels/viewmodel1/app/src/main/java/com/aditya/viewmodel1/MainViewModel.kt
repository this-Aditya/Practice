package com.aditya.viewmodel1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
class MainViewModel: ViewModel() {

    fun increase(){
        var i = 0
        viewModelScope.launch {
            for (j in 1..10){
            delay(2000)
            i++
            Log.i(TAG, "increase: $i")
        }
        }
    }
}