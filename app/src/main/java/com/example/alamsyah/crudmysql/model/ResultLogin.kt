package com.example.alamsyah.crudmysql.model
import com.google.gson.annotations.SerializedName
data class ResultLogin(
    @field:SerializedName("staff")
    val staff: Staff? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("status")
    val status: Int? = null
)