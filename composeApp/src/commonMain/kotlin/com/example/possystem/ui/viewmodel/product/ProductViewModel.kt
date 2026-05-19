package com.example.possystem.ui.viewmodel.product

import androidx.lifecycle.viewModelScope
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.domain.usecase.AddToCartUseCase
import com.example.possystem.domain.usecase.GetCartItemsUseCase
import com.example.possystem.domain.usecase.GetProductsUseCase
import com.example.possystem.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
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
        this.getCartItemsUseCase().onEach { items ->
            updateState { it.copy(cart = items) }
        }.launchIn(viewModelScope)
    }

    fun addToCart(product: Product) {
        this.addToCartUseCase(
            OrderItem(product = product, quantity = 1, priceAtTimeOfOrder = product.price)
        )
    }

    fun onGoToCart() {
        sendEffect(ProductUIEffect.NavigateToCart)
    }

    fun onGoToHistory() {
        sendEffect(ProductUIEffect.NavigateToHistory)
    }
}
