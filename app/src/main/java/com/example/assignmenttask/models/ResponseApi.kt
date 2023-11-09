package com.example.assignmenttask.models


import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Boolean
)