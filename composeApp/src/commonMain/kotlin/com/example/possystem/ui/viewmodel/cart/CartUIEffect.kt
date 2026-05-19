package com.example.possystem.ui.viewmodel.cart

sealed class CartUIEffect {

    object OrderPlaced: CartUIEffect()

    data class ShowError(val message: String): CartUIEffect()

    data class ShowPrintSuccess(val message: String): CartUIEffect()

}