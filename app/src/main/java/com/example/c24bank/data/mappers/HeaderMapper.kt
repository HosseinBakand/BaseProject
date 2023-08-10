package com.example.c24bank.data.mappers

import com.example.c24bank.data.api.model.HeaderResponse
import com.example.c24bank.domain.model.Header

fun HeaderResponse.toModel() = Header(
    headerTitle = headerTitle,
    headerDescription = headerDescription,
)