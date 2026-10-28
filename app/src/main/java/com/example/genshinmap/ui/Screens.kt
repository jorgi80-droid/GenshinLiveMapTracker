package com.example.genshinmap.ui
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshinmap.MainActivity
import com.example.genshinmap.core.map.MapImageStore
import com.example.genshinmap.feature.floating.FloatingCompanionService
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun GenshinMapApp(vm:AppViewModel=viewModel()){
 val context=LocalContext.current;val markers by vm.markers.collectAsState();val states by vm.states.collectAsState();val collected=remember(states){states.filter{it.status=="COLLECTED"}.map{it.markerId}.toSet()}
 var screen by remember{mutableStateOf("Map")};var imageName by remember{mutableStateOf<String?>(null)}
 val imagePicker=rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()){uri:Uri?->if(uri!=null){runCatching{context.contentResolver.takePersistableUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION)};imageName=MapImageStore(context).copyIntoAppStorage(uri,"map-${System.currentTimeMillis()}.img")?.name}}
 Scaffold(topBar={TopAppBar(title={Text("Genshin Live Map Tracker")})},bottomBar={NavigationBar{listOf("Map","Progress","Profiles","Companion").forEach{item->NavigationBarItem(selected=screen==item,onClick={screen=item},icon={},label={Text(item)})}}}){padding->Column(Modifier.padding(padding)){when(screen){
 "Map"->MapScreen(markers,collected,{vm.mark(it,it.id !in collected)}){imagePicker.launch(arrayOf("image/*"))}
 "Progress"->Column(Modifier.fillMaxSize().padding(20.dp)){Text("Collection progress",style=MaterialTheme.typography.headlineSmall);Text("${collected.size} collected markers");LinearProgressIndicator(progress={if(markers.isEmpty())0f else collected.size.toFloat()/markers.size},modifier=Modifier.fillMaxWidth())}
 "Profiles"->ProfileScreen(com.example.genshinmap.core.profile.ProfileStore(context))
 else->CompanionScreen()
}}}
}
@Composable private fun CompanionScreen(){val context=LocalContext.current;var message by remember{mutableStateOf("")};Column(Modifier.fillMaxSize().padding(20.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){Text("Floating companion",style=MaterialTheme.typography.headlineSmall);Text("Requires the system's Display over other apps permission. Does not read game memory.");Button(onClick={if(Build.VERSION.SDK_INT>=23&&!Settings.canDrawOverlays(context))context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:${context.packageName}"))) else {context.startForegroundService(Intent(context,FloatingCompanionService::class.java));message="Companion service requested"}}){Text("Enable floating companion")};Button(onClick={(context as? MainActivity)?.enterCompanionPip()}){Text("Enter picture-in-picture")};Text(message)}}
