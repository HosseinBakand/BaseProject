package com.example.baseproject.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true)
    val marketId: Int,
)