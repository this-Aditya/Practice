package com.aditya.roomcodelab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.roomcodelab.databinding.ExampleRowBinding
import com.aditya.roomcodelab.entity.Word


class WordsAdapter(
    val context: Context,
    val words: MutableList<Word>,
    val itemcicked: ItemClicked
) : RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {
    private lateinit var binding: ExampleRowBinding

    interface ItemClicked {
        fun onitemClicked(positon: Int)
    }

    inner class WordsViewHolder(binding: ExampleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindWord(word: Word) {
            binding.tvexp.text = word.word
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        binding = ExampleRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return WordsViewHolder(binding)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val word = words[position]
        holder.bindWord(word)
        binding.rowRv.setOnClickListener {
            itemcicked.onitemClicked(position)
        }
    }
}
