package com.example.c24bank.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceResponse(
    @SerialName("value")
    val value: Double? = null,
    @SerialName("currency")
    val currency: String? = null
)