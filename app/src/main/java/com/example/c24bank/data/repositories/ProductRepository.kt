package com.example.c24bank.data.repositories

import android.util.Log
import com.example.c24bank.data.api.RemoteDataSource
import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.data.database.daos.ProductDao
import com.example.c24bank.data.database.entities.ProductEntity
import com.example.c24bank.data.mappers.toEntity
import com.example.c24bank.data.mappers.toModel
import com.example.c24bank.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

interface ProductRepository {

    val products: Flow<List<Product>>
    suspend fun getProducts()
    fun getProduct(productId: Int): Flow<Product>
}

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val productDao: ProductDao,
) : ProductRepository {
    override val products: Flow<List<Product>>
        get() = productDao.getProducts().map { it.map(ProductEntity::toModel) }

    override suspend fun getProducts() {
        try {
            val products = remoteDataSource.getProducts().map(ProductResponse::toEntity)
            productDao.insertProducts(products)

        } catch (cancellationException: CancellationException) {
//            throw cancellationException//todo

        } catch (exception: Exception) {
            //todo
        }
    }

    override fun getProduct(productId: Int): Flow<Product> {
        return productDao.getProduct(productId).map(ProductEntity::toModel)
    }

}