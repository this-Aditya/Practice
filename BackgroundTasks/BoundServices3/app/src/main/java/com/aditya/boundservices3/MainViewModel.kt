package com.aditya.boundservices3

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainViewModel"
class MainViewModel: ViewModel() {
    private val isProgressupdating: MutableLiveData<Boolean> = MutableLiveData()
    private val myBinder: MutableLiveData<MyService.MyBinder?> = MutableLiveData()

    val _isProgressUpdating: LiveData<Boolean>
        get() = isProgressupdating
    val _myBinder: MutableLiveData<MyService.MyBinder?>
        get() = myBinder

    val serviceConnection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "onServiceConnected: Connected to service ")
            myBinder.postValue(binder as MyService.MyBinder)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myBinder.postValue(null)
        }

    }

}