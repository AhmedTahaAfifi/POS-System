package com.example.possystem.di

import com.example.possystem.data.datasource.POSDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}, database: POSDatabase) {
    startKoin {
        appDeclaration()
        modules(
            dataModule(database),
            domainModule,
            appModule
        )
    }
}