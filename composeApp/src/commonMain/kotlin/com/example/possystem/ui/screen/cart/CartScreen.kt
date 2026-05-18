package com.example.possystem.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.ui.components.CardItemRow
import com.example.possystem.ui.components.CheckOutBar
import com.example.possystem.ui.viewmodel.cart.CartUIState
import com.example.possystem.ui.viewmodel.cart.CartViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsState()

    CartScreenContent(
        state = state,
        onUpdateQuantity = { id, delta -> viewModel.updateQuantity(id, delta) },
        onRemoveItem = { id -> viewModel.removeItem(id) },
        onCheckout = { viewModel.checkout() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreenContent(
    state: CartUIState,
    onUpdateQuantity: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onCheckout: () -> Unit
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Your Cart") }) },
        bottomBar = {
            CheckOutBar(
                total = state.total,
                isProgressing = state.isProcessing,
                onCheckout = onCheckout
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.items.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Cart is Empty"
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.sdp),
                    verticalArrangement = Arrangement.spacedBy(12.sdp)
                ) {
                    items(state.items) { item ->
                        CardItemRow(
                            item = item,
                            onIncrease = { onUpdateQuantity(item.product.id, 1) },
                            onDecrease = { onUpdateQuantity(item.product.id, -1) },
                            onRemove = { onRemoveItem(item.product.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val mockProducts = listOf(
        Product(id = "1", name = "Product 1", price = 10.0, category = "Category A"),
        Product(id = "2", name = "Product 2", price = 20.0, category = "Category B"),
    )
    val mockItems = listOf(
        OrderItem(product = mockProducts[0], quantity = 2, priceAtTimeOfOrder = 10.0),
        OrderItem(product = mockProducts[1], quantity = 1, priceAtTimeOfOrder = 20.0),
    )
    val mockState = CartUIState(
        items = mockItems,
        total = 40.0,
        isProcessing = false
    )

    MaterialTheme {
        CartScreenContent(
            state = mockState,
            onUpdateQuantity = { _, _ -> },
            onRemoveItem = { _ -> },
            onCheckout = {}
        )
    }
}
