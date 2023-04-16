package com.aditya.room1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.room1.databinding.ExampleRowBinding
import com.aditya.room1.db.entity.Person

class PersonAdapter(val persons: List<Person>, val context: Context) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    private lateinit var binding: ExampleRowBinding

    inner class PersonViewHolder(binding: ExampleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPerson(person: Person) {
            binding.tvAge.text = person.age.toString()
            binding.tvId.text = person.id.toString()
            binding.tvFirstName.text = person.firstName
            binding.tvLastName.text = person.lastName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        binding = ExampleRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.bindPerson(person)
    }
}