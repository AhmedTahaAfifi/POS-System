package com.example.possystem.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.possystem.data.datasource.dao.OrderDao
import com.example.possystem.data.datasource.dao.ProductDao
import com.example.possystem.data.datasource.dao.TransactionDao
import com.example.possystem.data.datasource.entity.OrderEntity
import com.example.possystem.data.datasource.entity.OrderItemEntity
import com.example.possystem.data.datasource.entity.ProductEntity
import com.example.possystem.data.datasource.entity.TransactionEntity

@Database(
    entities = [
        ProductEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class POSDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun transactionDao(): TransactionDao

}