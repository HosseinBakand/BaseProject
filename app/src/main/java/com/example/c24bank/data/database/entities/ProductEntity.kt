package com.example.c24bank.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean,
    @ColumnInfo(name = "release_date")
    val releaseDate: Int,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
    @ColumnInfo(name = "long_description")
    val longDescription: String,
    val rating: Double,
    @ColumnInfo(name = "price_value")
    val priceValue: Double,
    @ColumnInfo(name = "price_currency")
    val priceCurrency: String,
)