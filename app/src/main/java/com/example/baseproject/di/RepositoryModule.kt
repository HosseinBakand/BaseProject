package com.example.baseproject.di

import com.example.baseproject.data.repositories.SampleRepository
import com.example.baseproject.data.repositories.SampleRepositoryImpl
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