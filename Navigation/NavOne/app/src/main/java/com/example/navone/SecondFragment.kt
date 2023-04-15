package com.example.navone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.navone.databinding.FragmentSeconddBinding

class SecondFragment : Fragment() {
lateinit var binding2: FragmentSeconddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding2 = FragmentSeconddBinding.inflate(inflater,container,false)
        val view = binding2.root
        binding2.tv2.setOnClickListener {
            Log.i("App", "onCreateView: $it")
            view.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        return view
    }

}