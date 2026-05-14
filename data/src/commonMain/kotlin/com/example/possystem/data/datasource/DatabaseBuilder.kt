package com.example.possystem.data.datasource

import androidx.room.RoomDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<POSDatabase>