package com.example.possystem.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.possystem.util.format

@Composable
fun CartSummaryBar(
    cartSize: Int,
    totalPrice: Double,
    onNavigateToCart: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Items in Cart: $cartSize", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Total: $${totalPrice.format()}", style = MaterialTheme.typography.titleMedium)
            }
            Button(onClick = onNavigateToCart) {
                Text("View Cart")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardSummaryBarPrev() {
    CartSummaryBar(
        cartSize = 50,
        totalPrice = 50.0,
        onNavigateToCart = {}
    )
}