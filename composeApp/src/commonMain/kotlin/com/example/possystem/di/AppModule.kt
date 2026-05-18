package com.example.possystem.di

import com.example.possystem.ui.viewmodel.cart.CartViewModel
import com.example.possystem.ui.viewmodel.orderHistory.OrderHistoryViewModel
import com.example.possystem.ui.viewmodel.product.ProductViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ProductViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::OrderHistoryViewModel)
}