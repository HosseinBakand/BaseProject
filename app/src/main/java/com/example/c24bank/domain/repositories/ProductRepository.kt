package com.example.c24bank.domain.repositories

import com.example.c24bank.domain.model.Header
import com.example.c24bank.domain.model.NetworkRequestState
import com.example.c24bank.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    val products: Flow<List<Product>>
    val header: Flow<Header?>
    fun getProduct(productId: Int): Flow<Product>
    suspend fun getProducts(): NetworkRequestState
    suspend fun addFavorite(productId: Int)
    suspend fun deleteFavorite(productId: Int)
}
