package com.example.alamsyah.crudmysql.presenter

import android.util.Log
import com.example.alamsyah.crudmysql.model.ResultStaff2
import com.example.alamsyah.crudmysql.model.ResultStatus
import com.example.alamsyah.crudmysql.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter2 (val crudView2: CrudView2) {

    //Fungsi GetData
    fun getData(ids: String?){
        NetworkConfig.getService().getSoal(ids).enqueue(object : Callback<ResultStaff2>{
                override fun onFailure(call: Call<ResultStaff2>, t: Throwable) {
                    crudView2.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultStaff2>, response: Response<ResultStaff2>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.staff
                            crudView2.onSuccessGet(data)
                        } else{
                            crudView2.onFailedGet("Error $status")
                        }
                    }
                }

            })
    }


    //Add data
    fun addData(name : String){
        NetworkConfig.getService()
            .addStaff(name)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView2.errorAdd(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView2.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView2.errorAdd(response.body()?.pesan ?: "")
                    }
                }

            })
    }


    //Jawab
    fun jawabSoal(jawab: String, nimkey: String, ujian: String, soal: String){
        NetworkConfig.getService()
            .jawabSoal(jawab, nimkey, ujian, soal)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView2.errorAdd(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView2.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView2.errorAdd(response.body()?.pesan ?: "")
                    }
                }

            })
    }


    //Hapus Data
    fun hapusData(id: String?){
        NetworkConfig.getService()
            .deleteStaff(id)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView2.onErrorDelete(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView2.onSuccessDelete(response.body()?.pesan ?: "")
                    } else {
                        crudView2.onErrorDelete(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data
    fun updateData(id: String, name: String){
        NetworkConfig.getService()
            .updateStaff(id, name)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView2.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView2.onSuccessUpdate(response.body()?.pesan ?: "")
                    }else{
                        crudView2.onErrorUpdate(response.body()?.pesan ?: "")
                    }

                }

            })
    }

}





