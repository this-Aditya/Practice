package com.aditya.room1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aditya.room1.databinding.FragmentListBinding
import com.aditya.room1.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

}