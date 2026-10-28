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
@Composable fun MapScreen(markers:List<MarkerEntity>,collected:Set<String>,onToggle:(MarkerEntity)->Unit,onImportMap:()->Unit) {
 var query by remember{mutableStateOf("")}
 Column(Modifier.fillMaxSize().padding(16.dp)){
  Text("Interactive map",style=MaterialTheme.typography.headlineSmall);Text("Sample markers only — import your own licensed map image/dataset.")
  OutlinedTextField(query,{query=it},label={Text("Search markers")},modifier=Modifier.fillMaxWidth())
  Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xFF171A22))){
   Canvas(Modifier.fillMaxSize().pointerInput(markers,collected){detectTapGestures{tap->val hit=markers.minByOrNull{m->val dx=m.x*size.width-tap.x;val dy=m.y*size.height-tap.y;dx*dx+dy*dy};if(hit!=null)onToggle(hit)}}){
    markers.forEach{m->drawCircle(if(m.id in collected)Color(0xFF5EE6A8) else Color(0xFFFFC857),8f,Offset((m.x*size.width).toFloat(),(m.y*size.height).toFloat()))}
   }
  }
  OutlinedButton(onClick=onImportMap,modifier=Modifier.fillMaxWidth()){Text("Import map image")}
  LazyColumn(Modifier.heightIn(max=180.dp)){items(markers.filter{it.name.contains(query,true)||it.description.contains(query,true)}){m->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Column(Modifier.weight(1f)){Text(m.name);Text("${m.region} • ${m.type}",style=MaterialTheme.typography.bodySmall)};Checkbox(checked=m.id in collected,onCheckedChange={onToggle(m)})};HorizontalDivider()}}
 }
}