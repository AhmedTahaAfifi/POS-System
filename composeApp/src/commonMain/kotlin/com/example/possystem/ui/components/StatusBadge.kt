package com.example.possystem.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.possystem.domain.model.OrderStatus
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun StatusBadge(status: OrderStatus) {
    val color = when(status) {
        OrderStatus.PENDING -> Color.Yellow
        OrderStatus.COMPLETED -> Color.Green
        OrderStatus.CANCELLED -> Color.Red
    }

    Surface(
        modifier = Modifier.padding(top = 4.sdp),
        color = color.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.sdp, vertical = 2.sdp),
            text = status.name,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}