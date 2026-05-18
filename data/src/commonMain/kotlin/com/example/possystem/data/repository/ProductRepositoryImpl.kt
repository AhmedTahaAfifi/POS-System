package com.example.possystem.data.repository

import com.example.possystem.data.datasource.dao.ProductDao
import com.example.possystem.data.mapper.ProductMapper
import com.example.possystem.domain.model.Product
import com.example.possystem.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { entities ->
            entities.map { ProductMapper.mapToDomain(it) }
        }
    }

    override fun getProductById(id: String): Flow<Product?> {
        return productDao.getProductById(id).map { entity ->
            entity?.let { ProductMapper.mapToDomain(it) }
        }
    }
}
