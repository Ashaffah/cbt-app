package com.example.alamsyah.crudmysql.model

import com.google.gson.annotations.SerializedName

data class ResultStaff2 (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("staff")
    val staff: List<DataItem2>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)