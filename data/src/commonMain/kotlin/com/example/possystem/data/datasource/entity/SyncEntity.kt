package com.example.possystem.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class SyncStatus {
    PENDING,
    SYNCED,
    FAILED
}

@Entity(tableName = "sync_queue")
data class SyncEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entityType: String,
    val entityId: String,
    val operation: String,
    val timestamp: Long,
    val status: SyncStatus = SyncStatus.PENDING,
    val retryCount: Int = 0
)
