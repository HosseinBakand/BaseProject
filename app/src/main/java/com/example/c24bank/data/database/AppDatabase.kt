package com.example.c24bank.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.c24bank.data.database.daos.SampleDao
import com.example.c24bank.data.database.entities.SampleEntity

@Database(
    entities = [
        SampleEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}
