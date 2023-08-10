package com.example.c24bank.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.c24bank.data.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<ProductEntity>>
    @Query("SELECT * FROM product WHERE id = :productId")
    fun getProduct(productId:Int): Flow<ProductEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(entity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)//TODO upsert
    suspend fun insertProducts(entities: List<ProductEntity>)
}