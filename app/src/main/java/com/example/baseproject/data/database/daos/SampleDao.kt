package com.example.baseproject.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.baseproject.data.database.entities.SampleEntity

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSample(entity: SampleEntity)
}