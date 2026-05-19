package com.example.possystem.domain.repository

import com.example.possystem.domain.model.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface CartRepository {

    val cartItems: StateFlow<List<OrderItem>>
    fun addToCart(item: OrderItem)
    fun removeFromCart(productId: String)
    fun updateQuantity(productId: String, delta: Int)
    fun clearCart()

}