package com.example.possystem.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.domain.model.Product
import com.example.possystem.util.format
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun CardItemRow(
    item: OrderItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.sdp, horizontal = 8.sdp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.sdp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "$${item.priceAtTimeOfOrder.format()}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onDecrease) { Text("-") }
                Text(
                    modifier = Modifier.padding(horizontal = 8.sdp),
                    text = item.quantity.toString()
                )
                IconButton(onClick = onIncrease) { Text("+") }
                IconButton(onClick = onRemove) { Text("❌") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardItemRowPreview() {
    val mockProduct = Product(id = "1", name = "Sample Product", price = 25.0, category = "General")
    val mockItem = OrderItem(product = mockProduct, quantity = 3, priceAtTimeOfOrder = 25.0)

    MaterialTheme {
        CardItemRow(
            item = mockItem,
            onIncrease = {},
            onDecrease = {},
            onRemove = {}
        )
    }
}
