package com.example.assignmenttask.models


import com.google.gson.annotations.SerializedName

data class Top3(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("giftcoin")
    val giftcoin: Int,
    @SerializedName("hive_id")
    val hiveId: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("profile_pic")
    val profilePic: String,
    @SerializedName("user_tag")
    val userTag: String,
    @SerializedName("userid")
    val userid: String,
    @SerializedName("username")
    val username: String
)