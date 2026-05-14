package com.example.possystem.data.mapper

import com.example.possystem.data.datasource.entity.OrderEntity
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus

object OrderMapper {
    fun mapToDomain(entity: OrderEntity, items: List<com.example.possystem.domain.model.OrderItem>): Order {
        return Order(
            id = entity.id,
            items = items,
            total = entity.total,
            timestamp = entity.timestamp,
            status = OrderStatus.valueOf(entity.status)
        )
    }

    fun mapToEntity(domain: Order): OrderEntity {
        return OrderEntity(
            id = domain.id,
            total = domain.total,
            timestamp = domain.timestamp,
            status = domain.status.name
        )
    }
}
