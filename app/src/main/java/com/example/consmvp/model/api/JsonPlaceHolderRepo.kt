package com.example.consmvp.model.api

import com.example.consmvp.model.api.RetrofitHelper.Companion.retrofit
import com.example.consmvp.model.api.entities.User

class JsonPlaceHolderRepo(){

    val api = retrofit.create(JsonplaceholderAPI::class.java)

    suspend fun getUsers(): List<User> {
        try {
            return api.getUsers()
        }catch (e: Exception){
            return emptyList()
        }
    }
}
