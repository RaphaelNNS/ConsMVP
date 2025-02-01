package com.example.consmvp.model.api

import com.example.consmvp.model.api.entities.User
import retrofit2.http.GET

interface JsonplaceholderAPI {
    @GET("users")
    suspend fun getUsers(): List<User>
}