package com.example.c24bank.data.repositoriesimpl

import com.example.c24bank.data.api.RemoteDataSource
import com.example.c24bank.data.api.model.ProductResponse
import com.example.c24bank.data.database.daos.ProductDao
import com.example.c24bank.data.database.entities.ProductEntity
import com.example.c24bank.data.mappers.toEntity
import com.example.c24bank.data.mappers.toModel
import com.example.c24bank.di.IoDispatcher
import com.example.c24bank.domain.model.Header
import com.example.c24bank.domain.model.NetworkRequestState
import com.example.c24bank.domain.model.Product
import com.example.c24bank.domain.repositories.ProductRepository
import com.example.c24bank.ui.screens.temp.DetailUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val productDao: ProductDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ProductRepository {
    override val products: Flow<List<Product>>
        get() = productDao.getProducts().map { it.map(ProductEntity::toModel) }

    override val header: MutableStateFlow<Header?> = MutableStateFlow(null)

    override suspend fun getProducts(): NetworkRequestState = withContext(dispatcher) {
        try {
            val remoteData = remoteDataSource.getProducts()
            val products = remoteData.products.map(ProductResponse::toEntity)
            productDao.insertProducts(products)
            header.emit(remoteData.header?.toModel())
            NetworkRequestState.Success
        } catch (exception: Exception) {
            NetworkRequestState.Error(exception)
        }
    }

    override suspend fun addFavorite(productId: Int) = withContext(dispatcher) {
        val product = getProduct(productId).first().copy(isFavorite = true)
        productDao.insertProduct(product.toEntity())
    }

    override suspend fun deleteFavorite(productId: Int) = withContext(dispatcher) {
        val product = getProduct(productId).first().copy(isFavorite = false)
        productDao.insertProduct(product.toEntity())
    }

    override fun getProduct(productId: Int): Flow<Product> {
        return productDao.getProduct(productId).map(ProductEntity::toModel)
    }

}