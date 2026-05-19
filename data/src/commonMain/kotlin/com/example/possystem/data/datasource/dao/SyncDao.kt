package com.example.possystem.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.possystem.data.datasource.entity.SyncEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncDao {

    @Insert
    suspend fun enqueue(syncEntity: SyncEntity)

    @Query("SELECT * FROM sync_queue WHERE status = 'PENDING' OR status = 'FAILED' ORDER BY timestamp ASC")
    fun getPendingSyncs(): Flow<List<SyncEntity>>

    @Query("SELECT * FROM sync_queue WHERE id = :id")
    suspend fun getSyncById(id: Long): SyncEntity?

    @Update
    suspend fun update(syncEntity: SyncEntity)

    @Query("DELETE FROM sync_queue WHERE id = :id")
    suspend fun delete(id: Long)

}