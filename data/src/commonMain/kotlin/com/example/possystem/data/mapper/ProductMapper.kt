package com.example.possystem.data.mapper

import com.example.possystem.data.datasource.entity.ProductEntity
import com.example.possystem.domain.model.Product

object ProductMapper {
    fun mapToDomain(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            category = entity.category
        )
    }

    fun mapToEntity(domain: Product): ProductEntity {
        return ProductEntity(
            id = domain.id,
            name = domain.name,
            price = domain.price,
            category = domain.category
        )
    }
}
