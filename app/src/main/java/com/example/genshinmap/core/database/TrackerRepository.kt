package com.example.genshinmap.core.database

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow

class TrackerRepository(val database: TrackerDatabase) {
    fun markers(): Flow<List<MarkerEntity>> = database.markerDao().observeMarkers()
    fun states(): Flow<List<CollectionStateEntity>> = database.collectionDao().observeStates()
    fun collectedCount(): Flow<Int> = database.collectionDao().observeCollectedCount()

    suspend fun setCollected(marker: MarkerEntity, collected: Boolean, note: String = "") {
        database.withTransaction {
            database.collectionDao().upsert(
                CollectionStateEntity(
                    markerId = marker.id,
                    status = if (collected) "COLLECTED" else "NOT_COLLECTED",
                    note = note
                )
            )
        }
    }

    suspend fun importMarkers(markers: List<MarkerEntity>) {
        database.withTransaction { database.markerDao().upsertMarkers(markers) }
    }
}