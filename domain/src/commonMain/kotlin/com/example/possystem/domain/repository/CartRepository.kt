package com.example.possystem.domain.repository

import com.example.possystem.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<List<OrderItem>>
    fun addToCart(item: OrderItem)
    fun removeFromCart(productId: String)
    fun updateQuantity(productId: String, delta: Int)
    fun clearCart()

}