package com.example.alamsyah.crudmysql

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.alamsyah.crudmysql.adapter.DataAdapter2
import com.example.alamsyah.crudmysql.model.DataItem
import com.example.alamsyah.crudmysql.model.DataItem2


import com.example.alamsyah.crudmysql.presenter.CrudView2

import com.example.alamsyah.crudmysql.presenter.Presenter2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rvCategory

import kotlinx.android.synthetic.main.activity_soal.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_soal.btnTambah as btnTambah1

class Soal : AppCompatActivity(), CrudView2 {
    val PREF_NAME = "Raffi"
    val NIM_KEY = "nim"
    val UJIAN_KEY = "ujian"
    lateinit var sharedPref: SharedPreferences
    private lateinit var presenter: Presenter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soal)
        presenter = Presenter2(this)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        val item = itemDataItem as DataItem?
        presenter.getData(sharedPref.getString(UJIAN_KEY, null).toString())

        btnTambah.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }

    }


    override fun onSuccessGet(data: List<DataItem2>?) {
        rvCategory.adapter = DataAdapter2(data,object : DataAdapter2.onClickItem{
            override fun clicked(item: DataItem2?) {
                startActivity<UpdateAddActivity>("dataItem2" to item)
            }


        })
    }

    override fun onFailedGet(msg: String) {
    }

    override fun onSuccessDelete(msg: String) {
        val itemDataItem = intent.getSerializableExtra("dataItem")
        val item = itemDataItem as DataItem?
        presenter.getData(item?.staffId ?: "")

    }

    override fun onErrorDelete(msg: String) {
        alert {
            title = "Error Delete Data"
        }.show()
    }

    override fun successAdd(msg: String) {
    }

    override fun errorAdd(msg: String) {
    }

    override fun onSuccessUpdate(msg: String) {
    }

    override fun onErrorUpdate(msg: String) {
    }
}