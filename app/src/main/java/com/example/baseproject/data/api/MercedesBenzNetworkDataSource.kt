package com.example.baseproject.data.api

import javax.inject.Inject
import javax.inject.Singleton

interface RemoteDataSource {
    suspend fun getSth(): Unit
}

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val networkApi: AppNetworkApi
) : RemoteDataSource {
    override suspend fun getSth() =     Unit


}