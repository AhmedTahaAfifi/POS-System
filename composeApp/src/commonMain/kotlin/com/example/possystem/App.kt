package com.example.possystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.possystem.ui.navigation.NavHost
import com.example.possystem.ui.navigation.Screen

@Composable
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.ProductList) }

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                currentScreen = currentScreen,
                onNavigate = { nextScreen ->
                    currentScreen = nextScreen
                }
            )
        }
    }
}
