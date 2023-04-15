package com.example.myapplication.data.api

import com.example.myapplication.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") id: Int
    ): Response<Post>
    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId:Int,
        @Query("_sort") sort:String,
        @Query("_order") order:String
    ):Response<List<Post>>

    @GET("posts")
    suspend fun getPostByMap(
        @Query("userId") userId:Int,
        @QueryMap options:Map<String,String>
    ) :Response<List<Post>>
}