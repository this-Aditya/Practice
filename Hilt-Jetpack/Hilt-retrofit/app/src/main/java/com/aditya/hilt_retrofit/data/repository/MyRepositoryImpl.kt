package com.aditya.hilt_retrofit.data.repository

import com.aditya.hilt_retrofit.data.remote.MyApi
import com.aditya.hilt_retrofit.domain.repository.MyRepository

class MyRepositoryImpl(private val api: MyApi) : MyRepository {

    override suspend fun doNetworkCall() {

    }
}