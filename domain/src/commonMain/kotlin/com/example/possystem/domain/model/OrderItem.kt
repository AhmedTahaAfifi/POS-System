package com.example.possystem.domain.model

data class OrderItem(
    val product: Product,
    val quantity: Int,
    val priceAtTimeOfOrder: Double
)
