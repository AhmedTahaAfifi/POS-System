package com.example.possystem.data.hardware

import com.example.possystem.domain.hardware.Printer
import com.example.possystem.domain.model.Order

class MockPrinter: Printer {
    override suspend fun printReceipt(order: Order): Result<Unit> {
        println("--- PRINTING RECEIPT ---")
        println("Order ID: ${order.id}")
        println("Items:")
        order.items.forEach { item ->
            println("${item.quantity}x ${item.product.name} - $${item.priceAtTimeOfOrder * item.quantity}")
        }
        println("Total: $${order.total}")
        println("------------------------")
        return Result.success(Unit)
    }
}