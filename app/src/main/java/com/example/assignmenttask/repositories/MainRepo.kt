package com.example.assignmenttask.repositories

import com.example.assignmenttask.models.ResponseApi
import com.example.assignmenttask.services.ApiService
import retrofit2.Call
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiService: ApiService) {
       fun getCallResponse(): Call<ResponseApi> = apiService.getLeaderboardData()
}