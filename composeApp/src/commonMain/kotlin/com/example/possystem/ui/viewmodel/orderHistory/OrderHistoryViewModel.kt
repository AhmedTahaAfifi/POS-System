package com.example.possystem.ui.viewmodel.orderHistory

import androidx.lifecycle.viewModelScope
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import com.example.possystem.domain.usecase.CreateOrderUseCase
import com.example.possystem.domain.usecase.GetOrdersUseCase
import com.example.possystem.domain.usecase.UpdateOrderStatusUseCase
import com.example.possystem.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase
): BaseViewModel<OrderHistoryUIState, OrderHistoryUIEffect>(OrderHistoryUIState()) {

    init {
        loadOrder()
    }

    private fun loadOrder() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = false) }
            getOrdersUseCase().catch { e ->
                updateState { it.copy(isLoading = false, error = e.message) }
                sendEffect(OrderHistoryUIEffect.ShowError(e.message ?: "Failed to load orders"))
            }.collect { orders ->
                updateState { it.copy(orders = orders, isLoading = false) }
            }
        }
    }

    fun placeOrder(order: Order) {
        viewModelScope.launch {
            try {
                createOrderUseCase(order)
                sendEffect(OrderHistoryUIEffect.OrderSavedSuccessfully)
                loadOrder()
            } catch (e: Exception) {
                sendEffect(OrderHistoryUIEffect.ShowError(e.message ?: "Error saving order"))
            }
        }
    }

    fun changeOrderStatus(orderId: String, newState: OrderStatus) {
        viewModelScope.launch {
            try {
                updateOrderStatusUseCase(orderId, newState)
                loadOrder()
            } catch (e: Exception) {
                sendEffect(OrderHistoryUIEffect.ShowError(e.message ?: "Error updating status"))
            }
        }
    }

}