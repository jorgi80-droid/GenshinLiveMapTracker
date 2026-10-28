package com.example.genshinmap.core.database
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao interface MarkerDao {
 @Query("SELECT * FROM markers ORDER BY region,name") fun observeMarkers():Flow<List<MarkerEntity>>
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsertMarkers(markers:List<MarkerEntity>)
 @Query("SELECT * FROM markers WHERE region=:region ORDER BY name") fun observeMarkersByRegion(region:String):Flow<List<MarkerEntity>>
 @Query("SELECT * FROM markers WHERE type=:type ORDER BY region,name") fun observeMarkersByType(type:String):Flow<List<MarkerEntity>>
}
@Dao interface CollectionDao {
 @Query("SELECT * FROM collection_state ORDER BY updatedAt DESC") fun observeStates():Flow<List<CollectionStateEntity>>
 @Query("SELECT * FROM collection_state WHERE markerId=:markerId LIMIT 1") suspend fun getState(markerId:String):CollectionStateEntity?
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun upsert(state:CollectionStateEntity)
 @Query("SELECT COUNT(*) FROM collection_state WHERE status='COLLECTED'") fun observeCollectedCount():Flow<Int>
}