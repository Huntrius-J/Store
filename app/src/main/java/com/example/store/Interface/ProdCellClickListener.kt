package com.example.store.Interface

import com.example.store.Models.Cart
import com.example.store.Models.Product

interface ProdCellClickListener {
    fun cellClickListener(data: Product)
}