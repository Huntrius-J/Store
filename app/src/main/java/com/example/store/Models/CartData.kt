package com.example.store.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CartData (
            @PrimaryKey(autoGenerate = true) val id: Int =0,
            @ColumnInfo(name = "product_id") var product_id: Int?,
            @ColumnInfo(name = "name") var name: String?,
            @ColumnInfo(name = "price") var price: String?,
            @ColumnInfo(name = "quanity") var quanity: Int?,
            @ColumnInfo(name = "image") var image: String?
        )