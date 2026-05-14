package com.example.possystem.domain.repository

import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun saveOrder(order: Order)
    fun getAllOrders(): Flow<List<Order>>
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
}