package com.example.livedata

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.combine

class MainViewModel(): ViewModel() {

    private var _second:MutableLiveData<Int> = MutableLiveData()
    var isFinsihed:MutableLiveData<Boolean> = MutableLiveData()
    fun second() = _second

    fun startTimer()
    {
        isFinsihed.value= false
        val time = object : CountDownTimer(25000,1000){
            override fun onTick(p0: Long) {
                val time:Int = (p0/1000).toInt();
                _second.value = time
                Log.i("MainActivity", "onTick: $time")
            }

            override fun onFinish() {
                showToast()
                isFinsihed.value =true
            }

        }.start()
    }

    private fun showToast() {
    }
}