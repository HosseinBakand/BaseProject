package com.example.baseproject.di

import android.content.Context
import androidx.room.Room
import com.example.baseproject.data.database.AppDatabase
import com.example.baseproject.data.database.daos.SampleDao
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
    ): SampleDao = database.sampleDao()
}
