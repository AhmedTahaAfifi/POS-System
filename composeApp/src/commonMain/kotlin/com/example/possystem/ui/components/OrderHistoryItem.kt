package com.example.possystem.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.possystem.domain.model.Order
import com.example.possystem.domain.model.OrderStatus
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun OrderHistoryItem(
    order: Order,
    onStatusChange: (OrderStatus) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.sdp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Order #${order.id}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Total: $${order.total}",
                    style = MaterialTheme.typography.bodyMedium
                )
                StatusBadge(status = order.status)
            }

            Button(onClick = {
                val nextStatus = when(order.status) {
                    OrderStatus.PENDING -> OrderStatus.COMPLETED
                    OrderStatus.COMPLETED -> OrderStatus.CANCELLED
                    OrderStatus.CANCELLED -> OrderStatus.PENDING
                }
                onStatusChange(nextStatus)
            }) {
                Text("Change Status")
            }
        }
    }
}

@Preview
@Composable
fun OrderHistoryItemPreview() {
    OrderHistoryItem(
        order = Order(
            id = "order_1234",
            items = emptyList(),
            total = 120.5,
            status = OrderStatus.PENDING,
            timestamp = 0L
        ),
        onStatusChange = {}
    )
}
