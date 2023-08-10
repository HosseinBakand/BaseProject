package com.example.c24bank.data.mappers

import com.example.c24bank.data.api.model.PriceResponse
import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.domain.model.Price
import com.example.c24bank.domain.model.Product

fun ProductResponse.toModel() = Product(
    id = id,
    name = name ?: "",
    shortDescription = description ?: "",
    isFavorite = false,//TODO
    imageUrl = imageURL ?: "",
    isAvailable = available ?: false,
    releaseDate = releaseDate ?: 0,
    longDescription = longDescription ?: "",
    rating = rating ?:0.0,
    price = price?.toModel() ?: Price(0.0,""),
)

fun PriceResponse.toModel() = Price(
    value = value ?: 0.0,
    currency = currency ?: "N/A"
)