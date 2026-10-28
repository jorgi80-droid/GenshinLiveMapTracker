package com.example.genshinmap.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.genshinmap.App
import com.example.genshinmap.core.database.MarkerEntity
import com.example.genshinmap.core.dataset.DatasetImporter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as App
    private val repo = app.repository
    val markers = repo.markers().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val states = repo.states().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val collectedCount = repo.collectedCount().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    init {
        viewModelScope.launch {
            if (repo.markers().first().isEmpty()) DatasetImporter.importJson(application, repo.database)
        }
    }

    fun mark(marker: MarkerEntity, collected: Boolean) {
        viewModelScope.launch { repo.setCollected(marker, collected) }
    }
}