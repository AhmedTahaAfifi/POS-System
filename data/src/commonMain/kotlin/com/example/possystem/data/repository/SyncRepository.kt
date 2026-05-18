package com.example.possystem.data.repository

import com.example.possystem.data.datasource.dao.SyncDao
import com.example.possystem.data.datasource.entity.SyncEntity
import com.example.possystem.data.datasource.entity.SyncStatus
import kotlinx.coroutines.flow.Flow

class SyncRepository(private val syncDao: SyncDao) {

    suspend fun scheduleSync(entityId: String, type: String, operation: String) {
        this.syncDao.enqueue(
            SyncEntity(
                entityId = entityId,
                entityType = type,
                operation = operation,
                timestamp = 0L
            )
        )
    }

    fun getSyncQueue(): Flow<List<SyncEntity>> {
        return this.syncDao.getPendingSyncs()
    }

    suspend fun markAsSynced(id: Long) {
        this.syncDao.delete(id)
    }

    suspend fun markAsFailed(id: Long) {
        val entity = this.syncDao.getSyncById(id)
        entity?.let {
            this.syncDao.update(it.copy(
                status = SyncStatus.FAILED,
                retryCount = it.retryCount + 1
            ))
        }
    }

}