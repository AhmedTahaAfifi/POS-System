package com.example.possystem.data.datasource.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.possystem.data.datasource.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: String): Flow<ProductEntity?>
}