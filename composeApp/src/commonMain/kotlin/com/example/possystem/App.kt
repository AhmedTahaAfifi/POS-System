package com.example.possystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.possystem.ui.navigation.NavHost
import com.example.possystem.ui.navigation.Screen
import com.example.possystem.util.BackHandler

@Composable
fun App() {
    MaterialTheme {
        // Simple navigation stack implementation
        var navigationStack by remember { mutableStateOf(listOf<Screen>(Screen.ProductList)) }
        
        val currentScreen = navigationStack.last()

        BackHandler(enabled = navigationStack.size > 1) {
            navigationStack = navigationStack.dropLast(1)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                currentScreen = currentScreen,
                onNavigate = { nextScreen ->
                    navigationStack = navigationStack + nextScreen
                },
                onBack = {
                    if (navigationStack.size > 1) {
                        navigationStack = navigationStack.dropLast(1)
                    }
                }
            )
        }
    }
}
