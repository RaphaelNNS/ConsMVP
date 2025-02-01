package com.example.consmvp.presenter

import com.example.consmvp.model.api.JsonPlaceHolderRepo
import com.example.consmvp.model.api.JsonplaceholderAPI
import com.example.consmvp.model.api.RetrofitHelper.Companion.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class UsersPresenter(
    val jsonRepo: JsonPlaceHolderRepo,
    var view: IUsersView
) {


    fun upadateList(){
        view.showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            val usersList = jsonRepo.getUsers()
            withContext(Dispatchers.Main){
                view.showListOnView(usersList)
                view.hideLoading()
            }
        }
    }

}