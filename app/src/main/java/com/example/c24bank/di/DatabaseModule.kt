package com.example.c24bank.di

import android.content.Context
import androidx.room.Room
import com.example.c24bank.data.database.AppDatabase
import com.example.c24bank.data.database.daos.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app-database",
    ).build()

    @Provides
    fun providesCarModelDao(
        database: AppDatabase,
    ): ProductDao = database.productDao()
}
