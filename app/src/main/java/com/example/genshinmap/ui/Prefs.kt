package com.example.genshinmap.ui
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
private val Context.dataStore by preferencesDataStore("tracker_preferences")
private val REGION_KEY=stringPreferencesKey("selected_region")
class TrackerPrefs(private val context:Context){val region=context.dataStore.data.map{it[REGION_KEY]?:"Mondstadt"};suspend fun setRegion(region:String){context.dataStore.edit{it[REGION_KEY]=region}}}