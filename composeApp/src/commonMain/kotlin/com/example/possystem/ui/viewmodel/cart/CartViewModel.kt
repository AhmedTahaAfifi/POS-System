package com.example.possystem.ui.viewmodel.cart

import androidx.lifecycle.viewModelScope
import com.example.possystem.domain.hardware.Printer
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import com.example.possystem.domain.usecase.ClearCartUseCase
import com.example.possystem.domain.usecase.CreateOrderUseCase
import com.example.possystem.domain.usecase.GetCartItemsUseCase
import com.example.possystem.domain.usecase.RemoveFromCartUseCase
import com.example.possystem.domain.usecase.UpdateCartQuantityUseCase
import com.example.possystem.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartViewModel(
    private val cartItemsUseCase: GetCartItemsUseCase,
    private val updateCartQuantityUseCase: UpdateCartQuantityUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val printer: Printer
) : BaseViewModel<CartUIState, CartUIEffect>(CartUIState()) {

    init {
        observeCart()
    }

    private fun observeCart() {
        this.cartItemsUseCase().onEach { items ->
            println("CartViewModel: Observed items: ${items.size}")
            updateState {
                it.copy(
                    items = items,
                    total = items.sumOf { item -> item.priceAtTimeOfOrder * item.quantity }
                )
            }
        }.launchIn(viewModelScope)
    }

    fun updateQuantity(productId: String, delta: Int) {
        this.updateCartQuantityUseCase(productId, delta)
    }

    fun removeItem(productId: String) {
        this.removeFromCartUseCase(productId)
    }

    fun checkout() {
        viewModelScope.launch {
            cartItemsUseCase().catch { e ->
                updateState { it.copy(isProcessing = false) }
                sendEffect(CartUIEffect.ShowError(e.message ?: "Failed to load cart"))
            }.collect { currentItems ->
                if (currentItems.isEmpty()) {
                    sendEffect(CartUIEffect.ShowError("Cart is empty!"))
                    return@collect
                }

                updateState { it.copy(isProcessing = true) }
                try {
                    val order = Order(
                        id = "ORD_${Random.nextInt(1000, 9999)}",
                        items = currentItems,
                        total = currentItems.sumOf { it.priceAtTimeOfOrder * it.quantity },
                        timestamp = 0L,
                        status = OrderStatus.PENDING
                    )
                    createOrderUseCase(order)
                    printReceipt(order)
                    clearCartUseCase()
                    sendEffect(CartUIEffect.OrderPlaced)
                } catch (e: Exception) {
                    sendEffect(CartUIEffect.ShowError(e.message ?: "Checkout failed"))
                } finally {
                    updateState { it.copy(isProcessing = false) }
                }
            }
        }
    }

    fun printReceipt(order: Order) {
        viewModelScope.launch {
            val result = printer.printReceipt(order)
            if (result.isSuccess) {
                sendEffect(CartUIEffect.ShowPrintSuccess("Receipt printed successfully!"))
            } else {
                sendEffect(CartUIEffect.ShowError("Printing failed!"))
            }
        }
    }

}