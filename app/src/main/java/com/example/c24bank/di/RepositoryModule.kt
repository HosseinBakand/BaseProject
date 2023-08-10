package com.example.c24bank.di

import com.example.c24bank.data.repositoriesimpl.ProductRepositoryImpl
import com.example.c24bank.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getProductRepository(repository: ProductRepositoryImpl): ProductRepository
}