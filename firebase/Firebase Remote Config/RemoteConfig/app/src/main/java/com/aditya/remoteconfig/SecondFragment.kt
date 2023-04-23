package com.aditya.remoteconfig

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.aditya.remoteconfig.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private  val TAG = "FirstFragment"
private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: Second")
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: Second")
        binding.tvSecond.setOnClickListener { 
            view.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Second")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: Second")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: Second")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Second")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: Second")
    }

}