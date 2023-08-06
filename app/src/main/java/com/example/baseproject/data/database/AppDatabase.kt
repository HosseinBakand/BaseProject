package com.example.baseproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseproject.data.database.daos.SampleDao
import com.example.baseproject.data.database.entities.SampleEntity

@Database(
    entities = [
        SampleEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}
