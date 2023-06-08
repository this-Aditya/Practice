package com.aditya.hilt_retrofit.domain.repository

interface MyRepository {
    suspend fun doNetworkCall()
}