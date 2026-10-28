package com.example.genshinmap.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.genshinmap.core.profile.ProfileStore
import com.example.genshinmap.core.profile.TrackerProfile
import java.util.UUID
@Composable fun ProfileScreen(store:ProfileStore){
 var profiles by remember{mutableStateOf(store.all())};var name by remember{mutableStateOf("")};var ar by remember{mutableStateOf("1")}
 Column(Modifier.fillMaxSize().padding(16.dp)){Text("Profiles",style=MaterialTheme.typography.headlineSmall);OutlinedTextField(name,{name=it},label={Text("Profile name")},modifier=Modifier.fillMaxWidth());OutlinedTextField(ar,{ar=it.filter(Char::isDigit).take(2)},label={Text("Adventure Rank")},modifier=Modifier.fillMaxWidth());Button(onClick={if(name.isNotBlank()){store.save(TrackerProfile(UUID.randomUUID().toString(),name.trim(),0,ar.toIntOrNull()?:1,"Mondstadt"));profiles=store.all();name=""}}){Text("Add profile")};LazyColumn{items(profiles){p->ListItem(headlineContent={Text(p.name)},supportingContent={Text("AR ${p.ar} • ${p.region}")},trailingContent={TextButton(onClick={store.delete(p.id);profiles=store.all()}){Text("Delete")}})}}}
}