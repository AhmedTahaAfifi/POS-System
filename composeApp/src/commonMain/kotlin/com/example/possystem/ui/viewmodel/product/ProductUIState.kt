package com.example.possystem.ui.viewmodel.product

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product

data class ProductUIState(
    val products: List<Product> = emptyList(),
    val cart: List<OrderItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
