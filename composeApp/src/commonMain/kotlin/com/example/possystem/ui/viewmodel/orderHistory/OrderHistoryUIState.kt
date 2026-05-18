package com.example.possystem.ui.viewmodel.orderHistory

import com.example.possystem.domain.model.Order

data class OrderHistoryUIState(
    val orders: List<Order> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
