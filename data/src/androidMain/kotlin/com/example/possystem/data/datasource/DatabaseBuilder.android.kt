package com.example.possystem.data.datasource

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

lateinit var appContext: Context

actual fun getDatabaseBuilder(): RoomDatabase.Builder<POSDatabase> {
    return Room.databaseBuilder<POSDatabase>(
        context = appContext,
        name = "pos_db.db"
    )
}