package com.example.possystem.data.datasource

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.possystem.data.datasource.dao.OrderDao
import com.example.possystem.data.datasource.dao.ProductDao
import com.example.possystem.data.datasource.dao.TransactionDao
import com.example.possystem.data.datasource.entity.OrderEntity
import com.example.possystem.data.datasource.entity.OrderItemEntity
import com.example.possystem.data.datasource.entity.ProductEntity
import com.example.possystem.data.datasource.entity.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

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
@ConstructedBy(POSDatabaseConstructor::class)
abstract class POSDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun transactionDao(): TransactionDao

}

fun getDatabase(builder: RoomDatabase.Builder<POSDatabase>): POSDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@Suppress("KotlinNoActualForExpect")
expect object POSDatabaseConstructor : RoomDatabaseConstructor<POSDatabase> {
    override fun initialize(): POSDatabase
}
