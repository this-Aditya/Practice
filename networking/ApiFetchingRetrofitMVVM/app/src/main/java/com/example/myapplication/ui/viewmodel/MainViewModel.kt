package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Post
import com.example.myapplication.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    var myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    var myResponse3: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    var myResponse4: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            var response = repository.getPosts()
            myResponse.value = response
        }    }
        fun getPostById(id: Int) {
            viewModelScope.launch {
                val response2 = repository.getPostById(id)
                myResponse2.value = response2
            }
        }
    fun getPostByUserId(userId:Int,sort:String,order:String){
        viewModelScope.launch {
            val response3 = repository.getPostsByUserId(userId,sort,order)
            myResponse3.value = response3
        }
    }
    fun getPostsByMap(userId: Int,option:Map<String,String>){
        viewModelScope.launch {
            var response4 = repository.getPostsByMap(userId,option)
            myResponse4.value= response4
        }
    }

}