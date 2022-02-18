package com.example.alamsyah.crudmysql.presenter

import com.example.alamsyah.crudmysql.model.ResultLogin
import com.example.alamsyah.crudmysql.model.ResultStaff
import com.example.alamsyah.crudmysql.model.ResultStatus
import com.example.alamsyah.crudmysql.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class LoginPresenter (val loginView: LoginView) {
    fun login(email : String, password : String){
        NetworkConfig.getService()
            .login(email,password)
            .enqueue(object : Callback<ResultLogin>{
                override fun onFailure(call: Call<ResultLogin>, t: Throwable) {
                    loginView.onFailedLogin(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultLogin>, response: Response<ResultLogin>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        loginView.onSuccessLogin(response.body()?.message, response.body()?.staff)
                    } else {
                        loginView.onFailedLogin(response.body()?.message)
                    }
                }
            })
    }
}