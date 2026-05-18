package com.example.possystem.di

import com.example.possystem.data.datasource.POSDatabase
import com.example.possystem.data.repository.OrderRepositoryImpl
import com.example.possystem.data.repository.ProductRepositoryImpl
import com.example.possystem.domain.repository.OrderRepository
import com.example.possystem.domain.repository.ProductRepository
import org.koin.dsl.module

fun dataModule(database: POSDatabase) = module {

    single { database.productDao() }
    single { database.orderDao() }
    single { database.transactionDao() }

    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<OrderRepository> { OrderRepositoryImpl(get()) }

}