package com.example.alamsyah.crudmysql

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alamsyah.crudmysql.adapter.DataAdapter
import com.example.alamsyah.crudmysql.model.DataItem
import com.example.alamsyah.crudmysql.presenter.CrudView
import com.example.alamsyah.crudmysql.presenter.Presenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_data2.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {
    val PREF_NAME = "Raffi"
    val NIM_KEY = "nim"
    val UJIAN_KEY = "ujian"
    lateinit var sharedPref: SharedPreferences
    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this)
        presenter.getData()
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        btnTambah.setOnClickListener {
            val editor:SharedPreferences.Editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            startActivity<Login>()
            finish()
        }
    }


    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory.adapter = DataAdapter(data,object : DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                val editor:SharedPreferences.Editor = sharedPref.edit()
                editor.putString(UJIAN_KEY, item?.staffId)
                editor.apply()
                startActivity<Soal>("dataItem" to item)
            }

            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                startActivity<MainActivity>()
                finish()
            }

        })
    }

    override fun onFailedGet(msg: String) {
    }

    override fun onSuccessDelete(msg: String) {
        presenter.getData()

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
