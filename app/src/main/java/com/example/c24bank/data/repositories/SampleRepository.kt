package com.example.c24bank.data.repositories

import com.example.c24bank.data.api.RemoteDataSource
import com.example.c24bank.data.database.daos.SampleDao
import javax.inject.Inject

interface SampleRepository {
    suspend fun nothing() : Boolean
}
class SampleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sampleDao: SampleDao,
) : SampleRepository {
    override suspend fun nothing(): Boolean {
        return true
    }

}