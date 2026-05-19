package com.example.possystem.data.repository

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartRepositoryImp: CartRepository {

    private val _cartItems = MutableStateFlow<List<OrderItem>>(emptyList())
    override val cartItems: StateFlow<List<OrderItem>> = _cartItems.asStateFlow()

    override fun addToCart(item: OrderItem) {
        this._cartItems.update { currentItem ->
            val existingItem = currentItem.find { it.product.id == item.product.id }
            if (existingItem != null) {
                currentItem.map {
                    if (it.product.id == item.product.id) it.copy(quantity = it.quantity + 1) else it
                }
            } else {
                currentItem + item
            }
        }
    }

    override fun removeFromCart(productId: String) {
        this._cartItems.update { it.filterNot { item -> item.product.id == productId } }
    }

    override fun updateQuantity(productId: String, delta: Int) {
        this._cartItems.update { currentCart ->
            currentCart.map { item ->
                if (item.product.id == productId) {
                    item.copy(quantity = (item.quantity + delta).coerceAtLeast(0))
                } else item
            }.filter { it.quantity > 0 }
        }
    }

    override fun clearCart() {
        this._cartItems.value = emptyList()
    }
}