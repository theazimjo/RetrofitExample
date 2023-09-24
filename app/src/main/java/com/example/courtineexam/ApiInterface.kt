package com.example.retrofit

import com.example.courtineexam.Todo
import com.example.courtineexam.User
import com.example.courtineexam.UserCreate
import com.example.courtineexam.UserListResponse
import com.example.courtineexam.userResponce
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
//    @GET("/todos")
//    suspend fun getTodoList(): Response<List<Todo>>

    @GET("/api/users")
    suspend fun getAllUserByPage(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<UserListResponse>

    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<userResponce>

    @POST("/api/users")
    suspend fun createuser(@Body userCreate: UserCreate): Response<UserCreate>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): Response<Unit>
}