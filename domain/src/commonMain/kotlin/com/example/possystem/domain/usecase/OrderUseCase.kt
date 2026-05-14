package com.example.possystem.domain.usecase

import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import com.example.possystem.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class CreateOrderUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(order: Order) {
        repository.saveOrder(order)
    }
}

class GetOrdersUseCase(private val repository: OrderRepository) {
    operator fun invoke(): Flow<List<Order>> {
        return repository.getAllOrders()
    }
}

class UpdateOrderStatusUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(orderId: String, status: OrderStatus) {
        repository.updateOrderStatus(orderId, status)
    }
}