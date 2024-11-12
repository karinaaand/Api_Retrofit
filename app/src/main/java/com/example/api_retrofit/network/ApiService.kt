package com.example.api_retrofit.network

import retrofit2.Call
import retrofit2.http.GET

// Interface ini mendefinisikan endpoint API yang akan digunakan oleh Retrofit untuk berkomunikasi dengan server
interface ApiService {

    // Menandakan bahwa ini adalah request HTTP GET untuk mengambil data dari endpoint "character"
    @GET("character")

    // Fungsi ini mengembalikan objek Call<ApiResponse>, yang akan berisi hasil response dari API
    fun getApiData(): Call<ApiResponse>
}
