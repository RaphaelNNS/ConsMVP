package com.example.consmvp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.consmvp.R
import com.example.consmvp.model.api.entities.User

class UsersAdapter(private val userList: List<User>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View): ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textName)
        val email: TextView = itemView.findViewById(R.id.textEmail)
        val city: TextView = itemView.findViewById(R.id.textCity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.user_recycler_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = user.name.toString()
        holder.email.text = user.email.toString()
        holder.city.text = user.email.toString()
    }


}