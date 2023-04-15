package com.example.myapplication.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapters.PostsAdapter
import com.example.myapplication.ui.viewmodel.MainViewModel
import com.example.myapplication.ui.viewmodelfactory.MainViewModelFactory

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var repository = Repository()
        var viewmodelfactory = MainViewModelFactory(repository)
        var viewmodel = ViewModelProvider(this, viewmodelfactory).get(MainViewModel::class.java)
        var option:HashMap<String,String> = HashMap()
        option.put("_sort","id")
        option.put("_order","desc")
        var posts = mutableListOf<Post>()
//        viewmodel.getPost()
//        viewmodel.myResponse.observe(this, Observer {
//            if (it.isSuccessful) {
//                var post = it.body()
//                binding.textView.text = post?.title.toString()
//            } else {
//                binding.textView.text = it.code().toString()
//            }
//        })


        //secondMethod
//        binding.button.setOnClickListener {
//            if (binding.etnum.text.isEmpty()){
//                Toast.makeText(this,"Please Provide a Valid Input ",Toast.LENGTH_SHORT).show()
//               binding.textView.text =""
//                return@setOnClickListener
//            }
//            else{
//               var id = binding.etnum.text.toString().toInt()
//               viewmodel.getPostById(id)
//               viewmodel.myResponse2.observe(this, Observer {
//               if (it.isSuccessful) {
//                   var post = it.body()
//                   binding.textView.text = post?.title
//               }
//               else{
//                   binding.textView.text = it.code().toString()
//               }
//               })
//            }
//        }


//        binding.button.setOnClickListener {
//            if (binding.etnum.text.isEmpty()){
//                Toast.makeText(this,"Please Provide a Valid Input ",Toast.LENGTH_SHORT).show()
//               binding.textView.text =""
//                return@setOnClickListener
//            }
//            else{
//                var userId = binding.etnum.text.toString().toInt()
//                binding.etnum.text.clear()
//                viewmodel.getPostsByMap(userId,option)
//                viewmodel.myResponse3.observe(this, Observer {
//                    if (it.isSuccessful){
//                         posts = it.body() as MutableList<Post>
//
//                    }
//                    else{
//                        binding.textView.text = it.code().toString()
//                        Log.i(TAG, "${it.code()}")
//                    }
//                })
//            }
//        }

        //getting costum posts for above logics there is button textview and ediittext
       viewmodel.getPostsByMap(2,option)
        viewmodel.myResponse4.observe(this, Observer {
            if (it.isSuccessful){
                posts= it.body() as MutableList<Post>
                binding.rvmain.adapter= PostsAdapter(this,posts)
                binding.rvmain.layoutManager =LinearLayoutManager(this)
            }
            else{
                Toast.makeText(this,"Error ${it.code()}",Toast.LENGTH_LONG)
            }
        })

    }
}