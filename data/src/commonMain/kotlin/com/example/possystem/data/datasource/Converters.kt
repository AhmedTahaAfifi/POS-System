package com.example.possystem.data.datasource

import androidx.room.TypeConverter
import com.example.possystem.domain.model.OrderStatus

class Converters {

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus): String = value.name

    @TypeConverter
    fun toOrderStatus(value: String): OrderStatus = OrderStatus.valueOf(value)

}