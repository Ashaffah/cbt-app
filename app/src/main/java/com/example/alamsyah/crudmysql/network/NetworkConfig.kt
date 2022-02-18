package com.example.alamsyah.crudmysql.network

import com.example.alamsyah.crudmysql.model.ResultLogin
import com.example.alamsyah.crudmysql.model.ResultStatus
import com.example.alamsyah.crudmysql.model.ResultStaff
import com.example.alamsyah.crudmysql.model.ResultStaff2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //Retrofit

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://127.0.0.1/cbtPage/rest/") //Change ur ip
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(StaffService::class.java)
}
interface StaffService{

    //Fungsi Create Data
    @FormUrlEncoded
    @POST("addStaff")
    fun addStaff(
        @Field("name") name: String
    ) : Call<ResultStatus>

    //Fungsi Create Data
    @FormUrlEncoded
    @POST("jawabSoal")
    fun jawabSoal(
        @Field("jawab") jawab: String,
        @Field("nimkey") nimkey: String,
        @Field("ujian") ujian: String,
        @Field("soal") soal: String
    ) : Call<ResultStatus>

    //Fungsi Get Data
    @GET("getData")

    fun getData() : Call<ResultStaff>

    //Fungsi Get Data
    @FormUrlEncoded
    @POST("getSoal")

    fun getSoal(@Field("id") id: String?): Call<ResultStaff2>

    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("deleteStaff")
    fun deleteStaff(@Field("id") id: String?) : Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
    @POST("updateStaff")
    fun updateStaff(
        @Field("id") id: String,
        @Field("name") name: String
    ) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("loginStaff")
    fun login(@Field("email") email : String?,
              @Field("password") password: String?) : Call<ResultLogin>

}
