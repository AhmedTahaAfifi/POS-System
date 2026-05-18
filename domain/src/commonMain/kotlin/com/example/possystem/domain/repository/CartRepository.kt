package com.example.possystem.domain.repository

import com.example.possystem.domain.model.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartRepository {

    private val _cartItems = MutableStateFlow<List<OrderItem>>(emptyList())
    val cartItem: StateFlow<List<OrderItem>> = _cartItems.asStateFlow()

    fun addToCart(item: OrderItem) {
        println("CartRepository: Adding item ${item.product.name}")
        _cartItems.update { currentItems ->
            val existingItem = currentItems.find { it.product.id == item.product.id }
            if (existingItem != null) {
                currentItems.map {
                    if (it.product.id == item.product.id) it.copy(quantity = it.quantity + 1) else it
                }
            } else {
                currentItems + item
            }
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.update { it.filterNot { item -> item.product.id == productId } }
    }

    fun updateQuantity(productId: String, delta: Int) {
        _cartItems.update { currentCart ->
            currentCart.map { item ->
                if (item.product.id == productId) {
                    item.copy(quantity = (item.quantity + delta).coerceAtLeast(0))
                } else item
            }.filter { it.quantity > 0 }
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

}