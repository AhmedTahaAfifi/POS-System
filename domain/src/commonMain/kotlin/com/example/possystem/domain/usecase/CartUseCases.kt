package com.example.possystem.domain.usecase

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.repository.CartRepository

class GetCartItemsUseCase(private val repository: CartRepository) {
    operator fun invoke() = repository.cartItems
}

class AddToCartUseCase(private val repository: CartRepository) {
    operator fun invoke(item: OrderItem) = repository.addToCart(item)
}

class RemoveFromCartUseCase(private val repository: CartRepository) {
    operator fun invoke(productId: String) = repository.removeFromCart(productId)
}

class UpdateCartQuantityUseCase(private val repository: CartRepository) {
    operator fun invoke(productId: String, delta: Int) = repository.updateQuantity(productId, delta)
}

class ClearCartUseCase(private val repository: CartRepository) {
    operator fun invoke() = repository.clearCart()
}