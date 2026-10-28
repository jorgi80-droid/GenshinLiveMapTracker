package com.example.genshinmap.feature.floating
import android.app.*
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
class FloatingCompanionService:Service() {
 private var wm:WindowManager?=null;private var panel:LinearLayout?=null
 override fun onCreate(){super.onCreate(); if(Build.VERSION.SDK_INT>=26)getSystemService(NotificationManager::class.java).createNotificationChannel(NotificationChannel("tracker","Map companion",NotificationManager.IMPORTANCE_LOW)); val n=if(Build.VERSION.SDK_INT>=26)Notification.Builder(this,"tracker") else @Suppress("DEPRECATION") Notification.Builder(this);startForeground(7301,n.setContentTitle("Genshin Map Tracker").setContentText("Floating companion active").setSmallIcon(android.R.drawable.ic_menu_mapmode).build())}
 override fun onStartCommand(intent:Intent?,flags:Int,startId:Int):Int {if(!Settings.canDrawOverlays(this)){stopSelf();return START_NOT_STICKY};if(panel==null)showPanel();return START_NOT_STICKY}
 private fun showPanel(){wm=getSystemService(WINDOW_SERVICE) as WindowManager;val box=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(12,8,12,8);setBackgroundColor(0xEE202430.toInt());addView(Button(this@FloatingCompanionService).apply{text="Genshin Tracker"});addView(Button(this@FloatingCompanionService).apply{text="Open app";setOnClickListener{packageManager.getLaunchIntentForPackage(packageName)?.let{startActivity(it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))}}});addView(Button(this@FloatingCompanionService).apply{text="Close";setOnClickListener{stopSelf()}})};val type=if(Build.VERSION.SDK_INT>=26)WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else @Suppress("DEPRECATION") WindowManager.LayoutParams.TYPE_PHONE;val p=WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,PixelFormat.TRANSLUCENT).apply{gravity=Gravity.TOP or Gravity.END;x=16;y=120};wm?.addView(box,p);panel=box}
 override fun onDestroy(){panel?.let{runCatching{wm?.removeView(it)}};panel=null;super.onDestroy()}
 override fun onBind(intent:Intent?):IBinder?=null
}