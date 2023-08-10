package com.example.c24bank.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsNetworkResponse(
    @SerialName("header")
    val header: HeaderResponse? = HeaderResponse(),
    @SerialName("filters")
    val filters: ArrayList<String> = arrayListOf(),
    @SerialName("products")
    val products: ArrayList<ProductResponse> = arrayListOf()
)
