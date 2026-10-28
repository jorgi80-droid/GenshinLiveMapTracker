package com.example.genshinmap.core.dataset
import android.content.Context
import com.example.genshinmap.core.database.TrackerDatabase
object DatasetImporter {
 suspend fun importJson(context:Context,database:TrackerDatabase,json:String?=null) {
  val body=json?:context.assets.open("sample_dataset.json").bufferedReader().use{it.readText()}
  val markers=Dataset.parse(body); database.markerDao().upsertMarkers(markers)
 }
}