package com.example.consmvp.presenter

import com.example.consmvp.model.api.entities.User

interface IUsersView: IView {
    fun showListOnView(users: List<User>)
}
