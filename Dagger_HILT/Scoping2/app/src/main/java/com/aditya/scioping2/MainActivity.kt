package com.aditya.scioping2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aditya.scioping2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    @Inject
//    lateinit var firstVar: FirstDependency Injecting fragment scoped dependency into activity wont work.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        println(firstVar.doFirstThing())
    }
}

class MainFragment() : Fragment() {

    @Inject
    lateinit var firstDependency: FirstDependency
}

// @Singleton Works Fine
// @ActivityScoped also Works Fine
@FragmentScoped // Gives an error in activity injection
class FirstDependency @Inject constructor() {
    fun doFirstThing() = "Hey, First thing done."
}