package com.example.c24bank.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeaderResponse(
    @SerialName("headerTitle")
    val headerTitle: String? = null,
    @SerialName("headerDescription")
    val headerDescription: String? = null
)
