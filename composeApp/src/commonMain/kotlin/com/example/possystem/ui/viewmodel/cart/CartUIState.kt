package com.example.possystem.ui.viewmodel.cart

import com.example.possystem.domain.model.OrderItem

data class CartUIState(
    val items: List<OrderItem> = emptyList(),
    val total: Double = 0.0,
    val isProcessing: Boolean = false
)