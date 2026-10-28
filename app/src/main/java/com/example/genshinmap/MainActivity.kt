package com.example.genshinmap
import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.genshinmap.ui.GenshinMapApp
class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent { MaterialTheme { Surface(modifier=Modifier.fillMaxSize()) { GenshinMapApp() } } }
 }
 fun enterCompanionPip() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) enterPictureInPictureMode(PictureInPictureParams.Builder().setAspectRatio(Rational(16,9)).build())
 }
 override fun onPictureInPictureModeChanged(isInPictureInPictureMode:Boolean,newConfig:Configuration) { super.onPictureInPictureModeChanged(isInPictureInPictureMode,newConfig) }
}