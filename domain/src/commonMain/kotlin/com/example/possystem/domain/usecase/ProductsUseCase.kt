package com.example.possystem.domain.usecase

import com.example.possystem.domain.model.Product
import com.example.possystem.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getAllProducts()
    }
}

class GetProductUseCase(private val repository: ProductRepository) {
    operator fun invoke(id: String): Flow<Product?> {
        return repository.getProductById(id)
    }
}