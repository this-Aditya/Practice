package com.aditya.hilt1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aditya.hilt1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity /*@Inject constructor(val first: First) // This wont work now*/ : AppCompatActivity() {

    // Field Injection
    @Inject
    lateinit var first: First
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, first.doFirstThing())
        Log.i(TAG, first.doSecondThing())

    }
}

//Annotating constructor with @Inject will generate the instance of this class at compile time and
// assign it where it is needed at runtime.
class First @Inject constructor(val secondclass: Second){
    fun doFirstThing() = " Hi, first thing done!"
    fun doSecondThing() = secondclass.doSecondThing()
}

class Second @Inject constructor() {
    fun doSecondThing() = " Hey, I did the second thing too."
}



