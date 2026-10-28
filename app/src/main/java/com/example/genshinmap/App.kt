package com.example.genshinmap
import android.app.Application
import androidx.room.Room
import com.example.genshinmap.core.database.TrackerDatabase
import com.example.genshinmap.core.database.TrackerRepository
class App : Application() {
 lateinit var repository: TrackerRepository
 private set
 override fun onCreate() {
  super.onCreate()
  val db = Room.databaseBuilder(this, TrackerDatabase::class.java, "genshin-tracker.db").build()
  repository = TrackerRepository(db)
 }
}