package com.example.baseproject.data.repositories

import com.example.baseproject.data.api.RemoteDataSource
import com.example.baseproject.data.database.daos.SampleDao
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