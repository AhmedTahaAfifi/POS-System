package com.example.possystem

import androidx.compose.ui.window.ComposeUIViewController
import com.example.possystem.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
