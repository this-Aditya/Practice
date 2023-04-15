package com.example.diffutils

import androidx.recyclerview.widget.DiffUtil

class MyDiffutil(val oldList: List<Person>,val newList: List<Person>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
return newList.size   }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id==newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id!=newList[newItemPosition].id ->false
            oldList[oldItemPosition].age!=oldList[newItemPosition].age->false
            oldList[oldItemPosition].name!=newList[newItemPosition].name ->false
            else -> false
        }
    }

}