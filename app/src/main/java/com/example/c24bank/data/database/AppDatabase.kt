package com.example.c24bank.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.c24bank.data.database.daos.ProductDao
import com.example.c24bank.data.database.entities.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
