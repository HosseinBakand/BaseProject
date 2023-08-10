package com.example.c24bank.data.mappers

import com.example.c24bank.data.api.model.PriceResponse
import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.data.database.entities.ProductEntity
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
    rating = rating ?: 0.0,
    price = price?.toModel() ?: Price(0.0, ""),
)

fun ProductResponse.toEntity() = ProductEntity(
    id = id,
    name = name ?: "",
    shortDescription = description ?: "",
    isFavorite = false,//TODO
    imageUrl = imageURL ?: "",
    isAvailable = available ?: false,
    releaseDate = releaseDate ?: 0,
    longDescription = longDescription ?: "",
    rating = rating ?: 0.0,
    priceValue = price?.value ?: 0.0,
    priceCurrency = price?.currency ?: "",
)

fun ProductEntity.toModel() = Product(
    id = id,
    name = name,
    shortDescription = shortDescription,
    isFavorite = isFavorite,
    imageUrl = imageUrl,
    isAvailable = isAvailable,
    releaseDate = releaseDate,
    longDescription = longDescription,
    rating = rating,
    price = Price(priceValue, priceCurrency),
)

fun Product.toEntity() = ProductEntity(
    id = id,
    name = name,
    shortDescription = shortDescription,
    isFavorite = isFavorite,
    imageUrl = imageUrl,
    isAvailable = isAvailable,
    releaseDate = releaseDate,
    longDescription = longDescription,
    rating = rating,
    priceValue = price.value,
    priceCurrency = price.currency,
)

fun PriceResponse.toModel() = Price(
    value = value ?: 0.0,
    currency = currency ?: "N/A"
)