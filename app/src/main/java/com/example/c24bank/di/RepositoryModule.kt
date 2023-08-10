package com.example.c24bank.di

import com.example.c24bank.data.repositories.ProductRepository
import com.example.c24bank.data.repositories.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getMarketRepository(repository: ProductRepositoryImpl): ProductRepository
}