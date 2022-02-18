package com.example.alamsyah.crudmysql.presenter

import com.example.alamsyah.crudmysql.model.DataItem
import com.example.alamsyah.crudmysql.model.Staff

interface LoginView {
    fun onSuccessLogin (msg : String?, data : Staff?)
    fun onFailedLogin (msg : String?)
}