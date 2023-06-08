package com.aditya.hilt_retrofit.di

import com.aditya.hilt_retrofit.data.remote.MyApi
import com.aditya.hilt_retrofit.data.repository.MyRepositoryImpl
import com.aditya.hilt_retrofit.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): MyApi = Retrofit.Builder()
        .baseUrl("https://demourl.com")
        .build()
        .create(MyApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: MyApi): MyRepository = MyRepositoryImpl(api)



}