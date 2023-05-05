package com.example.store.Models

data class Product (
    var id: Int? = null,
    var title: String? = null,
    var price: String? = null,
    var description: String? = null,
    var category: String? = null,
    var image: String? = null,
    var rating: Rating? = Rating()
        )