package com.example.possystem.di

import com.example.possystem.data.datasource.POSDatabase
import com.example.possystem.data.datasource.SyncManager
import com.example.possystem.data.datasource.getDatabase
import com.example.possystem.data.datasource.getDatabaseBuilder
import com.example.possystem.data.datasource.seeder.ProductSeeder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

@OptIn(DelicateCoroutinesApi::class)
fun initKoin(appDeclaration: KoinAppDeclaration = {}, database: POSDatabase) {
    if (KoinPlatform.getKoinOrNull() == null) {
        startKoin {
            appDeclaration()
            modules(
                dataModule(database),
                domainModule,
                appModule
            )
        }
    }

    // Seed the database with initial products
    val koin = KoinPlatform.getKoin()
    val seeder = koin.get<ProductSeeder>()
    GlobalScope.launch {
        seeder.seed()
    }

    // Start the background synchronization process
    val syncManager = koin.get<SyncManager>()
    syncManager.startSync()
}

fun initKoin() {
    val database = getDatabase(getDatabaseBuilder())
    initKoin(database = database)
}
