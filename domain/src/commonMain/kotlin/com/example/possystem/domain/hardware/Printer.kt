package com.example.possystem.domain.hardware

import com.example.possystem.domain.model.Order

interface Printer {
    suspend fun printReceipt(order: Order): Result<Unit>
}