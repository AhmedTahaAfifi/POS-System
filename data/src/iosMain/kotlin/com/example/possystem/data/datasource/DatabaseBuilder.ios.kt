package com.example.possystem.data.datasource

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

actual fun getDatabaseBuilder(): RoomDatabase.Builder<POSDatabase> {
    val dbFile = NSHomeDirectory() + "/pos_db.db"
    return Room.databaseBuilder<POSDatabase>(
        name = dbFile
    )
}