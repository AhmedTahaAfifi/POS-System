package com.example.possystem.data.mapper

import com.example.possystem.data.datasource.entity.TransactionEntity
import com.example.possystem.domain.model.Transaction

object TransactionMapper {
    fun mapToDomain(entity: TransactionEntity): Transaction {
        return Transaction(
            id = entity.id,
            orderId = entity.orderId,
            paymentMethod = entity.paymentMethod,
            timestamp = entity.timestamp
        )
    }

    fun mapToEntity(domain: Transaction): TransactionEntity {
        return TransactionEntity(
            id = domain.id,
            orderId = domain.orderId,
            paymentMethod = domain.paymentMethod,
            timestamp = domain.timestamp
        )
    }
}
