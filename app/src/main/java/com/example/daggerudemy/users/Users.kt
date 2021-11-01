package com.example.daggerudemy.users

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("displayed_name") val name:String,
    @SerializedName("profile_image") val imageUrl:String
)