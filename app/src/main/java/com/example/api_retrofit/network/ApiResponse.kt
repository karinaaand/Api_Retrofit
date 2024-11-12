package com.example.api_retrofit.network

import com.example.api_retrofit.model.ApiData
import com.google.gson.annotations.SerializedName

// Data class ini digunakan untuk merepresentasikan response dari API
// ApiResponse berfungsi untuk menampung data hasil API yang berupa list dari ApiData
data class ApiResponse(
    @SerializedName("results") // Menggunakan SerializedName untuk mencocokkan nama key pada JSON dengan properti di Kotlin
    val results: List<ApiData> // results akan berisi list data ApiData, yang merupakan objek yang telah didefinisikan sebelumnya
)
