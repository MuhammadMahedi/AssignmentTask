package com.example.assignmenttask.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("host_daily")
    val hostDaily: HostDaily
)