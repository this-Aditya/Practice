package com.aditya.room1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.aditya.room1.PersonViewModel
import com.aditya.room1.R
import com.aditya.room1.databinding.FragmentAddBinding
import com.aditya.room1.databinding.FragmentListBinding
import com.aditya.room1.db.entity.Person


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        binding.button.setOnClickListener {
            val fName = binding.etFirstName.text.toString()
            val lName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val person = Person(0,fName,lName,age)
            viewModel.addPerson(person)
            binding.root.findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

}