package com.example.consmvp.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.consmvp.R
import com.example.consmvp.model.api.JsonPlaceHolderRepo
import com.example.consmvp.model.api.entities.User
import com.example.consmvp.presenter.IUsersView
import com.example.consmvp.presenter.UsersPresenter
import com.example.consmvp.view.adapter.UsersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IUsersView {

    private lateinit var textCarregando: TextView
    private lateinit var presenter: UsersPresenter
    private lateinit var repo: JsonPlaceHolderRepo
    private lateinit var rv: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var uiScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        uiScope = CoroutineScope(Dispatchers.IO)
        textCarregando = findViewById(R.id.textCarregando)
        rv = findViewById(R.id.rvMainUser)
        repo = JsonPlaceHolderRepo()
        presenter = UsersPresenter  (repo, this, uiScope)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        presenter.upadateList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }



    override fun showListOnView(users: List<User>) {
        usersAdapter = UsersAdapter(users)
        rv.adapter = usersAdapter
    }

    override fun showLoading() {
        textCarregando.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        textCarregando.visibility = View.GONE
    }

    override fun showError() {
        var alert = AlertDialog.Builder(this)
        alert.setTitle("ERRO")
        alert.setMessage("Voce esta tentando efetuar uma operação não permitida.\n")
        alert.setNeutralButton("Voltar"){_,_ ->

        }
        alert.create().show()
    }
}