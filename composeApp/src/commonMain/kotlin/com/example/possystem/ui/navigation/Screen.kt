package com.example.possystem.ui.navigation

sealed class Screen(val route: String) {
    object ProductList : Screen("products")
    object Cart : Screen("cart")
    object OrderHistory : Screen("history")
}
