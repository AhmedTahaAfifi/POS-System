package com.example.possystem.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val orderId: String,
    val paymentMethod: String,
    val timestamp: Long
)
