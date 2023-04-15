package com.example.diffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val persons:MutableList<Person> = mutableListOf()
    lateinit var adapter:PersonAdapter
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataSet()
        setUpRecyclerView()
        adapter.setData(persons)
        var i=7
        binding.button.setOnClickListener {
            i++
            persons.add(Person(i,"Add $i","Age $i"));
            persons.add(Person(i,"Add${130-i}","Age ${100-i}"));
            adapter.setData(persons)
        }
    }

    private fun setUpRecyclerView() {
     adapter = PersonAdapter();
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)

    }

    private fun getDataSet() {
        for (i in 1.. 3){
            persons.add(Person(i,"Person $i","Age $i" ))
        }
}
}