package com.example.c24bank.domain.model

import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


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


val previewProducts = listOf(
    Product(
        id = 0,
        name = "Name",
        isFavorite = false,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/08.png",
        isAvailable = true,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    ),
    Product(
        id = 1,
        name = "Name2",
        isFavorite = true,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
        isAvailable = false,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    ),
    Product(
        id = 2,
        name = "Name2",
        isFavorite = true,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
        isAvailable = false,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    ),
    Product(
        id = 3,
        name = "Name2",
        isFavorite = true,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
        isAvailable = false,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    ),
    Product(
        id = 4,
        name = "Name2",
        isFavorite = true,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
        isAvailable = false,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    ),
    Product(
        id = 5,
        name = "Name2",
        isFavorite = true,
        imageUrl = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
        isAvailable = false,
        releaseDate = 12525,
        shortDescription = "short",
        longDescription = "long",
        rating = 3.5,
        price = Price(0.0, "$$"),
    )
)
