package com.aditya.room1.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.room1.PersonViewModel
import com.aditya.room1.R
import com.aditya.room1.adapter.PersonAdapter
import com.aditya.room1.databinding.FragmentListBinding
import com.aditya.room1.db.entity.Person


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel : PersonViewModel
    private lateinit var adapter: PersonAdapter
    private val persons: MutableList<Person> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        createRecyclerView()

        viewModel.getAllPersons.observe(viewLifecycleOwner, Observer {
            persons.clear()
            persons.addAll(it)
            adapter.notifyDataSetChanged()
        })
        binding.floatingActionButton.setOnClickListener{
            val view = binding.root
            view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun createRecyclerView() {
        adapter = PersonAdapter(persons,requireContext())
        binding.rvPersons.adapter = adapter
        binding.rvPersons.layoutManager = LinearLayoutManager(requireContext())
    }


}