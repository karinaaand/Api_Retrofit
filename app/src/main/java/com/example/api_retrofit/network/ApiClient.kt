package com.example.api_retrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Fungsi untuk mendapatkan instance dari ApiService dengan konfigurasi Retrofit
    fun getInstance(): ApiService {
        // Membuat HttpLoggingInterceptor untuk mencatat log request dan response HTTP
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)  // Menampilkan body dari request dan response

        // Membuat OkHttpClient dengan menambahkan interceptor untuk log
        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        // Membangun Retrofit instance dengan base URL dan GsonConverter untuk parsing JSON
        val builder = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")  // Menetapkan base URL API
            .addConverterFactory(GsonConverterFactory.create())  // Menambahkan converter untuk mengubah JSON ke objek Kotlin
            .client(mOkHttpClient)  // Menambahkan OkHttpClient yang sudah dikonfigurasi
            .build()

        // Mengembalikan instance dari ApiService untuk digunakan dalam komunikasi API
        return builder.create(ApiService::class.java)
    }
}
