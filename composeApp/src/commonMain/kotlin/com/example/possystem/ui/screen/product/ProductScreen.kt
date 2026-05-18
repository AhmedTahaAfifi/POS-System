package com.example.possystem.ui.screen.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.ui.components.CartSummaryBar
import com.example.possystem.ui.components.ProductCard
import com.example.possystem.ui.navigation.Screen
import com.example.possystem.ui.viewmodel.product.ProductUIEffect
import com.example.possystem.ui.viewmodel.product.ProductUIState
import com.example.possystem.ui.viewmodel.product.ProductViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = koinViewModel(),
    onNavigate: (Screen) -> Unit
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEffect.collect { effect ->
            when(effect) {
                is ProductUIEffect.NavigateToCart -> onNavigate(Screen.Cart)
                is ProductUIEffect.ShowError -> {}
            }
        }
    }

    ProductScreenContent(
        state = state,
        onAddToCart = { product -> viewModel.addToCart(product) },
        onNavigateToCart = { viewModel.onGoToCart() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreenContent(
    state: ProductUIState,
    onAddToCart: (Product) -> Unit,
    onNavigateToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("POS System - Products") }
            )
        },
        bottomBar = {
            CartSummaryBar(
                cartSize = state.cart.size,
                totalPrice = state.cart.sumOf { it.priceAtTimeOfOrder * it.quantity },
                onNavigateToCart = onNavigateToCart
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                ProductGrid(
                    products = state.products,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@Composable
fun ProductGrid(
    products: List<Product>,
    onAddToCart: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.sdp),
        contentPadding = PaddingValues(16.sdp),
        horizontalArrangement = Arrangement.spacedBy(16.sdp),
        verticalArrangement = Arrangement.spacedBy(16.sdp)
    ) {
        items(products) { product ->
            ProductCard(product, onAddToCart)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    val mockProducts = listOf(
        Product(id = "1", name = "Product 1", price = 10.0, category = "Category A"),
        Product(id = "2", name = "Product 2", price = 20.0, category = "Category B"),
        Product(id = "3", name = "Product 3", price = 30.0, category = "Category A"),
    )
    val mockState = ProductUIState(
        products = mockProducts,
        cart = listOf(
            OrderItem(product = mockProducts[0], quantity = 2, priceAtTimeOfOrder = 10.0)
        )
    )

    MaterialTheme {
        ProductScreenContent(
            state = mockState,
            onAddToCart = {},
            onNavigateToCart = {}
        )
    }
}


