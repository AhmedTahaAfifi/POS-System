package com.example.possystem.fakes

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeCartRepository : CartRepository {
    private val _cartItems = MutableStateFlow<List<OrderItem>>(emptyList())

    override fun getCartItems(): Flow<List<OrderItem>> {
        return _cartItems.asStateFlow()
    }

    override fun addToCart(item: OrderItem) {
        _cartItems.update { current ->
            val existing = current.find { it.product.id == item.product.id }
            if (existing != null) {
                current.map { if (it.product.id == item.product.id) it.copy(quantity = it.quantity + 1) else it }
            } else {
                current + item
            }
        }
    }

    override fun removeFromCart(productId: String) {
        _cartItems.update { it.filterNot { item -> item.product.id == productId } }
    }

    override fun updateQuantity(productId: String, delta: Int) {
        _cartItems.update { current ->
            current.map { item ->
                if (item.product.id == productId) {
                    item.copy(quantity = (item.quantity + delta).coerceAtLeast(0))
                } else item
            }.filter { it.quantity > 0 }
        }
    }

    override fun clearCart() {
        _cartItems.value = emptyList()
    }
}
