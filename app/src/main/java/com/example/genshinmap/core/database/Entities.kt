package com.example.genshinmap.core.database
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName="markers", indices=[Index("region"),Index("type")])
data class MarkerEntity(@PrimaryKey val id:String,val name:String,val type:String,val region:String,val x:Double,val y:Double,val description:String="")
@Entity(tableName="collection_state", indices=[Index("status")])
data class CollectionStateEntity(@PrimaryKey val markerId:String,val status:String,val updatedAt:Long=System.currentTimeMillis(),val note:String="")
@androidx.room.Database(entities=[MarkerEntity::class,CollectionStateEntity::class],version=1,exportSchema=false)
abstract class TrackerDatabase: androidx.room.RoomDatabase() {
 abstract fun markerDao(): MarkerDao
 abstract fun collectionDao(): CollectionDao
}