package com.example.possystem.ui.navigation

import androidx.compose.runtime.Composable
import com.example.possystem.ui.screen.product.ProductScreen
import com.example.possystem.ui.screen.cart.CartScreen
import com.example.possystem.ui.screen.orderHistory.OrderHistoryScreen

@Composable
fun NavHost(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    when (currentScreen) {
        is Screen.ProductList -> ProductScreen(
            onNavigate = { onNavigate(it) }
        )
        is Screen.Cart -> CartScreen(
            onBack = onBack
        )
        is Screen.OrderHistory -> OrderHistoryScreen(
            // We can add an onBack here too if we want
        )
    }
}
