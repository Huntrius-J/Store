package com.example.store.Common

import com.example.store.Interface.RetrofitServices
import com.example.store.Retrofit.RetrofitClient
import retrofit2.create

object Common {
    private val BASE_URL = "https://fakestoreapi.com/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}