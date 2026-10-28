package com.example.genshinmap.core.map
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
class MapImageStore(private val context:Context) {
 fun copyIntoAppStorage(uri:Uri,fileName:String):File?=runCatching { val target=File(context.filesDir,fileName); context.contentResolver.openInputStream(uri)?.use{input->target.outputStream().use{input.copyTo(it)}};target }.getOrNull()
 fun loadBitmap(file:File):Bitmap?=BitmapFactory.decodeFile(file.absolutePath)
}