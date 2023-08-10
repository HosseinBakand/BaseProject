package com.example.c24bank.di

import com.example.c24bank.data.repositories.SampleRepository
import com.example.c24bank.data.repositories.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getMarketRepository(repository: SampleRepositoryImpl): SampleRepository
}