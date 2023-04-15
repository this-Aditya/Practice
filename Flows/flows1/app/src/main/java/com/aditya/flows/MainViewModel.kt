package com.aditya.flows

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel(private var start: Int) : ViewModel() {
    private var job: Job = Job()
    val _time = MutableLiveData<Int>(start)
    private var state: Int? = null
    private var currentTime: Int = 0

    val countDownFlow = flow<Int> {
        val startTime = start;
         currentTime = startTime
        emit(currentTime)
        while (currentTime > 0) {
            delay(1000)
            currentTime--
            emit(currentTime)
            Log.i(TAG, "time: $currentTime and thred: ${Thread.currentThread().name} ")
        }
    }.flowOn(Dispatchers.IO)

    fun collectTime() {
        job.cancel()
        job = viewModelScope.launch {
            countDownFlow.collect { time ->
                _time.value = time
            }
        }
    }

    fun setDuration(duration: Int) {
        this.start = duration
        collectTime()
    }

    fun pause() {
     state = currentTime
        job.cancel()
    }

    fun resume() {
      setDuration(currentTime)
    }
}





