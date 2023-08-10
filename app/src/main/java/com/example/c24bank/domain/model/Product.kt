package com.example.c24bank.domain.model

import com.example.c24bank.data.api.model.PriceResponse

data class Product(
    val id: Int,
    val name: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val isAvailable: Boolean,
    val releaseDate: Int,
    val shortDescription: String,
    val longDescription: String,
    val rating: Double,
    val price: Price
)

