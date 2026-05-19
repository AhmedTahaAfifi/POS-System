package com.example.possystem.di

import com.example.possystem.data.datasource.POSDatabase
import com.example.possystem.data.datasource.SyncManager
import com.example.possystem.data.datasource.seeder.ProductSeeder
import com.example.possystem.data.repository.OrderRepositoryImpl
import com.example.possystem.data.repository.ProductRepositoryImpl
import com.example.possystem.data.repository.SyncRepository
import com.example.possystem.domain.hardware.Printer
import com.example.possystem.data.hardware.MockPrinter
import com.example.possystem.data.repository.CartRepositoryImp
import com.example.possystem.domain.repository.CartRepository
import com.example.possystem.domain.repository.OrderRepository
import com.example.possystem.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

fun dataModule(database: POSDatabase) = module {
    // DAOs
    single { database.productDao() }
    single { database.orderDao() }
    single { database.transactionDao() }
    single { database.syncDao() }

    // Seeder
    single { ProductSeeder(get()) }

    // Repositories
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<OrderRepository> { OrderRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImp() }
    single { SyncRepository(get()) }

    // Hardware
    single<Printer> { MockPrinter() }

    // Sync Manager
    single { 
        val syncScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        SyncManager(get(), syncScope) 
    }
}