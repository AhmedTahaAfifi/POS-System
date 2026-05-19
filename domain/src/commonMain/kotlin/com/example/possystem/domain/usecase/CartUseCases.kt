package com.example.possystem.domain.usecase

import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(private val repository: CartRepository) {
    operator fun invoke(): Flow<List<OrderItem>> {
        return repository.getCartItems()
    }
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