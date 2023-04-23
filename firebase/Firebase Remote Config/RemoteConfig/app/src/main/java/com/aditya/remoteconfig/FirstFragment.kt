package com.aditya.remoteconfig

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.aditya.remoteconfig.databinding.FragmentFirstBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.RemoteConfigConstants
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.aditya.remoteconfig.MainActivity

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private  val TAG = "FirstFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")

        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        binding.tvFirst.setOnClickListener{
            view.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        val showImage = Firebase.remoteConfig.getBoolean(MainActivity.SHOW_BACKGROUND_IMAGE)
        Log.i("Firebase", "Permission-> $showImage")
        if (showImage){
            binding.imageView.setBackgroundResource(R.drawable.ic_android)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}