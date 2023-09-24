package com.example.courtineexam

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("data")
    val user: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)