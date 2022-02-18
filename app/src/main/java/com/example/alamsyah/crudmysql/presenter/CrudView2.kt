package com.example.alamsyah.crudmysql.presenter

import com.example.alamsyah.crudmysql.model.DataItem
import com.example.alamsyah.crudmysql.model.DataItem2

interface CrudView2 {
    //Untuk get data
    fun onSuccessGet(data: List<DataItem2>?)
    fun onFailedGet(msg : String)

    //Untuk Delete
    fun onSuccessDelete(msg: String)
    fun onErrorDelete(msg: String)

    //Untuk Add
    fun successAdd(msg : String)
    fun errorAdd(msg: String)

    //Untuk Update
    fun onSuccessUpdate(msg : String)
    fun onErrorUpdate(msg : String)



}