package com.example.possystem.domain.model

data class Transaction(
    val id: String,
    val orderId: String,
    val paymentMethod: String,
    val timestamp: Long
)
