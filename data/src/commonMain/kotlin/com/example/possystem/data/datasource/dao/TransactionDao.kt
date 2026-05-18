package com.example.possystem.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.possystem.data.datasource.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE orderId = :orderId")
    fun getTransactionByOrderId(orderId: String): Flow<TransactionEntity?>

}