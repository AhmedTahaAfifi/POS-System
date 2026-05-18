package com.example.possystem.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.possystem.domain.model.Product
import com.example.possystem.util.format
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onAddToCart(product) }
    ) {
        Column(
            modifier = Modifier.padding(16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.sdp))
            Text(text = "$${product.price.format()}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.sdp))
            Button(onClick = { onAddToCart(product) }) {
                Text("Add to Cart")
            }
        }
    }
}