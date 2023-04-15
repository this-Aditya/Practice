package com.example.myapplication.data.repository

import com.example.myapplication.data.api.RetrofitInstance
import com.example.myapplication.data.model.Post
import retrofit2.Response
import java.util.*

class Repository {
    suspend fun getPosts() = RetrofitInstance.api.getPost()
    suspend fun getPostById(id: Int) = RetrofitInstance.api.getPostById(id)
    suspend fun getPostsByUserId(userid:Int,sort:String,order:String) =
        RetrofitInstance.api.getPostsByUserId(userid,sort,order)
    suspend fun getPostsByMap(userid: Int,option:Map<String,String>):Response<List<Post>> =
        RetrofitInstance.api.getPostByMap(userid,option)
}