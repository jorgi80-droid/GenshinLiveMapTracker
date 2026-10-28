package com.example.genshinmap.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.genshinmap.core.database.MarkerEntity

@Composable
fun MapScreen(markers: List<MarkerEntity>, collected: Set<String>, onToggle: (MarkerEntity) -> Unit, onImportMap: () -> Unit) {
    var query by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Interactive map", style = MaterialTheme.typography.headlineSmall)
        Text("Sample markers only — import your own licensed map image/dataset.")
        OutlinedTextField(query, { query = it }, label = { Text("Search markers") }, modifier = Modifier.fillMaxWidth())
        Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xFF171A22))) {
            Canvas(Modifier.fillMaxSize().pointerInput(markers, collected) {
                detectTapGestures { tap ->
                    val hit = markers.minByOrNull { marker ->
                        val dx = marker.x * size.width - tap.x
                        val dy = marker.y * size.height - tap.y
                        dx * dx + dy * dy
                    }
                    if (hit != null) onToggle(hit)
                }
            }) {
                markers.forEach { marker ->
                    drawCircle(
                        if (marker.id in collected) Color(0xFF5EE6A8) else Color(0xFFFFC857),
                        8f,
                        Offset((marker.x * size.width).toFloat(), (marker.y * size.height).toFloat())
                    )
                }
            }
        }
        OutlinedButton(onClick = onImportMap, modifier = Modifier.fillMaxWidth()) { Text("Import map image") }
        LazyColumn(Modifier.heightIn(max = 180.dp)) {
            items(markers.filter { it.name.contains(query, true) || it.description.contains(query, true) }) { marker ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(Modifier.weight(1f)) {
                        Text(marker.name)
                        Text("${marker.region} • ${marker.type}", style = MaterialTheme.typography.bodySmall)
                    }
                    Checkbox(checked = marker.id in collected, onCheckedChange = { onToggle(marker) })
                }
                HorizontalDivider()
            }
        }
    }
}