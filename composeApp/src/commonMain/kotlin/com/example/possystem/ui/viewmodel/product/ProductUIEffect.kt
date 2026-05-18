package com.example.possystem.ui.viewmodel.product

sealed class ProductUIEffect {

    data class ShowError(val message: String): ProductUIEffect()

    object NavigateToCart: ProductUIEffect()

}