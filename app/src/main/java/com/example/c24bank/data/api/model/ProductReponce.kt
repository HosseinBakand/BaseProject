package com.example.c24bank.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("color")
    val color: String? = null,
    @SerialName("imageURL")
    val imageURL: String? = null,
    @SerialName("colorCode")
    val colorCode: String? = null,
    @SerialName("available")
    val available: Boolean? = null,
    @SerialName("releaseDate")
    val releaseDate: Int? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("longDescription")
    val longDescription: String? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("price")
    val price: PriceResponse? = PriceResponse()
)

