package com.example.alamsyah.crudmysql

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alamsyah.crudmysql.model.Staff
import com.example.alamsyah.crudmysql.presenter.LoginPresenter
import com.example.alamsyah.crudmysql.presenter.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class Login : AppCompatActivity(), LoginView {
    private lateinit var presenter: LoginPresenter
    val PREF_NAME = "Raffi"
    val NIM_KEY = "nim"
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        btnLogin.onClick {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            presenter.login(email, password)
        }

    }
    override fun onSuccessLogin(msg: String?, data: Staff?) {
        val email = loginEmail.text.toString()
        alert {
            title = "Information"
            message = "Login Success"
        }.show()
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putString(NIM_KEY, email)
        editor.apply()
        startActivity<MainActivity>()
        finish()

    }

    override fun onFailedLogin(msg: String?) {
        alert {
            title = "Information"
            message = "Login Failed Silahkan Cek Username dan Password"
        }.show()
    }
}