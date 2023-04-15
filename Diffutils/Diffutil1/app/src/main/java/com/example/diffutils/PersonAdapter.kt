package com.example.diffutils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutils.databinding.RowLayoutBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
private var persons:List<Person> = emptyList()
    class PersonViewHolder(val binding:RowLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.PersonViewHolder {
return PersonViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
return persons.size   }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.tvName.text = persons[position].name.toString()
        holder.binding.tvAge.text = persons[position].age.toString()
    }

fun setData(personsUpdated:List<Person>){
    val diffUtil = MyDiffutil(persons,personsUpdated)
    val diffResults = DiffUtil.calculateDiff(diffUtil)
    persons = personsUpdated
    diffResults.dispatchUpdatesTo(this)

}
}
