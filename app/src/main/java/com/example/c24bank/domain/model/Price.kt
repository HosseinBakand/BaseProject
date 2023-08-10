package com.example.c24bank.domain.model

import java.util.Currency

data class Price(
    val value : Double,
    val currency: String
){
    override fun toString(): String {
        return "$value $currency"
    }
}
