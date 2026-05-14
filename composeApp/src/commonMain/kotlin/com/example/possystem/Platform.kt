package com.example.possystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform