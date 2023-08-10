package com.example.c24bank.data.repositories

import android.util.Log
import com.example.c24bank.data.api.RemoteDataSource
import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.data.database.daos.SampleDao
import com.example.c24bank.data.mappers.toModel
import com.example.c24bank.domain.model.Product
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sampleDao: SampleDao,
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return try {
            return remoteDataSource.getProducts().map(ProductResponse::toModel)
        } catch (cancellationException: CancellationException) {
            throw cancellationException//todo
            emptyList()
        } catch (exception: Exception) {
            emptyList()//todo
        }

    }

}