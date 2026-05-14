package com.example.possystem.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val id: String,
    val total: Double,
    val timestamp: Long,
    val status: String
)
