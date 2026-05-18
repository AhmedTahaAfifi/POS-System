package com.example.possystem.di

import com.example.possystem.data.datasource.POSDatabase
import com.example.possystem.data.datasource.getDatabase
import com.example.possystem.data.datasource.getDatabaseBuilder
import org.koin.mp.KoinPlatform
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

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
}

fun initKoin() {
    val database = getDatabase(getDatabaseBuilder())
    initKoin(database = database)
}
