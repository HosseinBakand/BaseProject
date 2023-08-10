package com.example.c24bank.ui.screens.home

import com.example.c24bank.domain.model.Product

data class ProductUiState(
    val products : List<Product> = emptyList()
)