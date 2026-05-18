package com.example.possystem.data.datasource

import com.example.possystem.data.datasource.entity.SyncEntity
import com.example.possystem.data.repository.SyncRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class SyncManager(
    private val syncRepository: SyncRepository,
    private val scope: CoroutineScope
) {

    fun startSync() {
        this.scope.launch {
            while (isActive) {
                val pending = syncRepository.getSyncQueue().first()

                pending.forEach { item ->
                    try {
                        simulateNetworkCall(item)
                        syncRepository.markAsSynced(item.id)
                    } catch (e: Exception) {
                        syncRepository.markAsFailed(item.id)
                    }
                }
                delay(10000)
            }
        }
    }

    private suspend fun simulateNetworkCall(item: SyncEntity) {
        delay(500)
        if (Random.nextDouble() < 0.2) throw Exception("Network Failure")
        println("Synced ${item.entityType} ${item.entityId} successfully")
    }

}