package com.example.possystem.data.datasource

import androidx.room.TypeConverters
import com.example.possystem.domain.model.OrderStatus

class Converters {

    @TypeConverters
    fun fromOrderStatus(value: OrderStatus): String = value.name

    @TypeConverters
    fun toOrderStatus(value: String): OrderStatus = OrderStatus.valueOf(value)

}