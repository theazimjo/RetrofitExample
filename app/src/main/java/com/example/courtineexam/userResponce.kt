package com.example.courtineexam

import com.google.gson.annotations.SerializedName

data class userResponce (
    @SerializedName("data")
    val user: User
)