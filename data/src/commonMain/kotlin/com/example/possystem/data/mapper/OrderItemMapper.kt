package com.example.possystem.data.mapper

import com.example.possystem.data.datasource.entity.OrderItemEntity
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product

object OrderItemMapper {
    fun mapToDomain(entity: OrderItemEntity, product: Product): OrderItem {
        return OrderItem(
            product = product,
            quantity = entity.quantity,
            priceAtTimeOfOrder = entity.priceAtTimeOfOrder
        )
    }

    fun mapToEntity(domain: OrderItem, orderId: String): OrderItemEntity {
        return OrderItemEntity(
            orderId = orderId,
            productId = domain.product.id,
            quantity = domain.quantity,
            priceAtTimeOfOrder = domain.priceAtTimeOfOrder
        )
    }
}
