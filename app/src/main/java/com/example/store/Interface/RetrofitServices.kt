package com.example.store.Interface

import com.example.store.Models.Cart
import com.example.store.Models.CartInfo
import com.example.store.Models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitServices {
    @GET("products/category/men's clothing")
    fun getMensClothesList(): Call<MutableList<Product>>

    @GET("products/category/women's clothing")
    fun getWomensClothesList(): Call<MutableList<Product>>

    @GET("carts")
    fun getAllCarts() : Call<MutableList<Cart>>

    @POST("users")
    fun registerNewUser()

    @POST("carts")
    fun addToCart() : Call<CartInfo>
}