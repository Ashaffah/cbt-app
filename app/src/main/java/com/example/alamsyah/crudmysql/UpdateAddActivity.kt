package com.example.alamsyah.crudmysql

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alamsyah.crudmysql.model.DataItem2
import com.example.alamsyah.crudmysql.presenter.CrudView2
import com.example.alamsyah.crudmysql.presenter.Presenter2
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView2 {
    val PREF_NAME = "Raffi"
    val NIM_KEY = "nim"
    val UJIAN_KEY = "ujian"
    lateinit var sharedPref: SharedPreferences

    private lateinit var presenter: Presenter2
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)
        sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        presenter = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem2")

        if (itemDataItem != null){
            btnAction.text = "Jawab"
            val item = itemDataItem as DataItem2?
            etName.setText(item?.staffName.toString())

            btnAction.onClick {
                presenter.jawabSoal(
                    etJawab.text.toString(),
                    sharedPref.getString(NIM_KEY, null).toString(),
                    sharedPref.getString(UJIAN_KEY, null).toString(),
                    item?.staffId.toString()
                    )
                finish()
            }

        }
    }



    override fun successAdd(msg: String) {
        startActivity<Soal>()
        finish()
    }

    override fun errorAdd(msg: String) {}

    override fun onSuccessUpdate(msg: String) {
        startActivity<Soal>()
        finish()
    }

    override fun onErrorUpdate(msg: String) {}

    override fun onSuccessGet(data: List<DataItem2>?) {}

    override fun onFailedGet(msg: String) {}

    override fun onSuccessDelete(msg: String) {}

    override fun onErrorDelete(msg: String) {}
}
