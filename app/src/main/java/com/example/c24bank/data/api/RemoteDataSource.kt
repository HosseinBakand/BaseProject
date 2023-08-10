package com.example.c24bank.data.api

import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.data.mappers.toModel
import com.example.c24bank.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

interface RemoteDataSource {
    suspend fun getProducts(): List<ProductResponse>
}

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val networkApi: AppNetworkApi
) : RemoteDataSource {
    override suspend fun getProducts(): List<ProductResponse> {
        return networkApi.getProducts().products
    }
}