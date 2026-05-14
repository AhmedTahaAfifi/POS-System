package com.example.possystem.domain.model

enum class OrderStatus {
    PENDING,
    COMPLETED,
    CANCELLED
}

data class Order(
    val id: String,
    val items: List<OrderItem>,
    val total: Double,
    val timestamp: Long,
    val status: OrderStatus
)