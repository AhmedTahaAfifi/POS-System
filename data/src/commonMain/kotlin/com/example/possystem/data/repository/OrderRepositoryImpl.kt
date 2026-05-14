package com.example.possystem.data.repository

import com.example.possystem.data.datasource.dao.OrderDao
import com.example.possystem.data.mapper.OrderMapper
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import com.example.possystem.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val orderDao: OrderDao
) : OrderRepository {
    override suspend fun saveOrder(order: Order) {
        orderDao.insertOrder(OrderMapper.mapToEntity(order))
    }

    override fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders().map { orderEntities ->
            orderEntities.map { entity ->
                OrderMapper.mapToDomain(entity, emptyList()) 
            }
        }
    }

    override suspend fun updateOrderStatus(orderId: String, status: OrderStatus) {
        orderDao.updateOrderStatus(orderId, status.name)
    }
}
