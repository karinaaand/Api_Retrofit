package com.example.api_retrofit.model

import com.google.gson.annotations.SerializedName

// Data class untuk menyimpan data yang diterima dari API dan memetakan data JSON ke objek Kotlin
data class ApiData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("gender")
    val gender: String
)
