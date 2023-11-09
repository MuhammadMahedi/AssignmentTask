package com.example.assignmenttask.services

import com.example.assignmenttask.MainActivity
import com.example.assignmenttask.models.ResponseApi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
        @GET(MainActivity.ENDPOINT)
        fun getLeaderboardData(): Call<ResponseApi>
}