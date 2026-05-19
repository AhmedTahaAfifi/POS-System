package com.example.possystem.ui.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.navigationBarsPadding
import com.example.possystem.domain.model.OrderItem
import com.example.possystem.ui.viewmodel.cart.CartUIEffect
import com.example.possystem.ui.viewmodel.cart.CartViewModel
import com.example.possystem.util.format
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEffect.collect { effect ->
            when(effect) {
                is CartUIEffect.OrderPlaced -> { /* Navigate back or show success */ }
                is CartUIEffect.ShowError -> { /* Show Snackbar with error */ }
                is CartUIEffect.ShowPrintSuccess -> { /* Show Snackbar: "Receipt printed!" */ }
            }
        }
    }

    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text("Your Cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            ) 
        },
        bottomBar = {
            CheckoutBar(
                total = state.total,
                isProcessing = state.isProcessing
            ) { viewModel.checkout() }
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
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.items) { item ->
                        CartItemRow(
                            item = item,
                            onIncrease = { viewModel.updateQuantity(item.product.id, 1) },
                            onDecrease = { viewModel.updateQuantity(item.product.id, -1) },
                            onRemove = { viewModel.removeItem(item.product.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: OrderItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${item.priceAtTimeOfOrder.format()}", style = MaterialTheme.typography.bodySmall)
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = onDecrease) { Text("-") }
                Text(text = item.quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                Button(onClick = onIncrease) { Text("+") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onRemove, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("X")
                }
            }
        }
    }
}

@Composable
fun CheckoutBar(total: Double, isProcessing: Boolean, onCheckout: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total: $${total.format()}", style = MaterialTheme.typography.titleLarge)
            Button(onClick = onCheckout, enabled = !isProcessing) {
                if (isProcessing) CircularProgressIndicator(modifier = Modifier.size(20.dp)) 
                else Text("Checkout")
            }
        }
    }
}
