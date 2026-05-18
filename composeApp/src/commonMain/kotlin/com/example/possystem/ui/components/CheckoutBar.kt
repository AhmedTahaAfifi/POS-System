package com.example.possystem.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.possystem.util.format
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun CheckOutBar(total: Double, isProgressing: Boolean, onCheckout: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.padding(16.sdp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Totle: $${total.format()}",
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = onCheckout,
                enabled = !isProgressing
            ) {
                if (isProgressing) CircularProgressIndicator(modifier = Modifier.size(20.sdp))
                else Text("Checkout")
            }
        }
    }
}