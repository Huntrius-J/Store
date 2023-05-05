package com.example.store.Models

import com.google.gson.annotations.SerializedName

data class CartInfo(

    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("products")
    var products: ArrayList<Products> = arrayListOf()

)