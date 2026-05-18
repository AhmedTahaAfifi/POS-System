package com.example.possystem.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.possystem.data.datasource.entity.OrderEntity
import com.example.possystem.data.datasource.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Query("UPDATE orders SET status = :status WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: String, status: String)

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    fun getOrderItemsForOrder(orderId: String): Flow<List<OrderItemEntity>>

}