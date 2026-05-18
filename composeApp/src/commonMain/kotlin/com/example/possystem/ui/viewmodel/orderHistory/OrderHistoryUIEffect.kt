package com.example.possystem.ui.viewmodel.orderHistory

sealed class OrderHistoryUIEffect {

    object OrderSavedSuccessfully: OrderHistoryUIEffect()

    data class ShowError(val message: String): OrderHistoryUIEffect()

}