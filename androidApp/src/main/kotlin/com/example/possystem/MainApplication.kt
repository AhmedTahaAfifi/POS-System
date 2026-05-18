package com.example.possystem

import android.app.Application
import com.example.possystem.data.datasource.appContext
import com.example.possystem.data.datasource.getDatabase
import com.example.possystem.data.datasource.getDatabaseBuilder
import com.example.possystem.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        val database = getDatabase(getDatabaseBuilder())
        initKoin(
            appDeclaration = {
                androidContext(this@MainApplication)
            },
            database = database,
        )
    }
}
