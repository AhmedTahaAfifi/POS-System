package com.example.possystem.ui.screen.orderHistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.possystem.ui.components.OrderHistoryItem
import com.example.possystem.ui.viewmodel.orderHistory.OrderHistoryViewModel
import network.chaintech.sdpcomposemultiplatform.sdp
import org.koin.compose.viewmodel.koinViewModel
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import com.example.possystem.ui.viewmodel.orderHistory.OrderHistoryUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    OrderHistoryContent(
        state = state,
        onStatusChange = { id, status -> viewModel.changeOrderStatus(id, status) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryContent(
    state: OrderHistoryUIState,
    onStatusChange: (String, OrderStatus) -> Unit
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Order History") }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.error,
                    color = MaterialTheme.colorScheme.error
                )
            } else if (state.orders.isEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "No orders found"
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.sdp),
                    verticalArrangement = Arrangement.spacedBy(12.sdp)
                ) {
                    items(state.orders) { order ->
                        OrderHistoryItem(
                            order = order,
                            onStatusChange = { newStatus -> 
                                onStatusChange(order.id, newStatus)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderHistoryScreenPreview() {
    MaterialTheme {
        OrderHistoryContent(
            state = OrderHistoryUIState(
                orders = listOf(
                    Order(
                        id = "order_1",
                        items = emptyList(),
                        total = 150.0,
                        timestamp = 0L,
                        status = OrderStatus.PENDING
                    ),
                    Order(
                        id = "order_2",
                        items = emptyList(),
                        total = 200.0,
                        timestamp = 0L,
                        status = OrderStatus.COMPLETED
                    )
                )
            ),
            onStatusChange = { _, _ -> }
        )
    }
}
