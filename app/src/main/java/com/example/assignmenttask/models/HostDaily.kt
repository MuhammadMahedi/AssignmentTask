package com.example.assignmenttask.models


import com.google.gson.annotations.SerializedName

data class HostDaily(
    @SerializedName("all")
    val all: List<All>,
    @SerializedName("top3")
    val top3: List<Top3>
)