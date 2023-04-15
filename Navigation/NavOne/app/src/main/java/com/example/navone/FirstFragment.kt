package com.example.navone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.navone.databinding.FragmentFirstBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
 const val TAG = "App"
class FirstFragment : Fragment() {
    private val scope = CoroutineScope(Dispatchers.IO + CoroutineName("MyScope"))
    lateinit var binding1: FragmentFirstBinding
 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     Log.i("App", "onCreateView: $container")
     binding1 = FragmentFirstBinding.inflate(inflater,container,false)
      val view = binding1.root
     binding1.tv1.setOnClickListener {
         view.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
     }
     val job = GlobalScope.launch {
         while (true) {
             delay(2000L)
             Log.i(TAG, "coroutine  : ")
         }}
         Log.i(TAG, "$job")


     return view
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
}