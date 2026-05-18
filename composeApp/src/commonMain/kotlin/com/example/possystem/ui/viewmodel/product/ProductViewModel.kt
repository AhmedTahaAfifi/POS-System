package com.example.possystem.ui.viewmodel.product

import androidx.lifecycle.viewModelScope
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.domain.repository.CartRepository
import com.example.possystem.domain.usecase.GetProductsUseCase
import com.example.possystem.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductViewModel(
    private val cartRepository: CartRepository,
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<ProductUIState, ProductUIEffect>(ProductUIState()) {

    init {
        loadProducts()
        observeCart()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            getProductsUseCase().catch { e ->
                updateState { it.copy(isLoading = false, error = e.message) }
                sendEffect(ProductUIEffect.ShowError(e.message ?: "Unknown Error"))
            }.collect { products ->
                updateState { it.copy(products = products, isLoading = false) }
            }
        }
    }

    private fun observeCart() {
        this.cartRepository.cartItem
            .onEach { items ->
                updateState { it.copy(cart = items) }
            }
            .launchIn(viewModelScope)
    }

    fun addToCart(product: Product) {
        this.cartRepository.addToCart(
            OrderItem(product = product, quantity = 1, priceAtTimeOfOrder = product.price)
        )
    }

    fun removeFromCart(product: Product) {
        this.cartRepository.removeFromCart(product.id)
    }

    fun updateQuantity(product: Product, delta: Int) {
        this.cartRepository.updateQuantity(product.id, delta)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }

    fun onGoToCart() {
        sendEffect(ProductUIEffect.NavigateToCart)
    }
}
