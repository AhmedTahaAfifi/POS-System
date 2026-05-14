package com.example.possystem.domain.repository

import com.example.possystem.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getProductById(id: String): Flow<Product?>
}