package com.example.assignmenttask.di

import com.example.assignmenttask.MainActivity
import com.example.assignmenttask.repositories.MainRepo
import com.example.assignmenttask.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun apiServiceProvider():ApiService = Retrofit.Builder()
        .baseUrl(MainActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun mainRepoProvider(apiService: ApiService) = MainRepo(apiService)
}