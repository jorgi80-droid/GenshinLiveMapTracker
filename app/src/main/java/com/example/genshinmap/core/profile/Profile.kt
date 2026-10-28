package com.example.genshinmap.core.profile
import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
private const val PREF="tracker_profiles"
data class TrackerProfile(val id:String,val name:String,val worldLevel:Int,val ar:Int,val region:String)
class ProfileStore(context:Context) {
 private val prefs=context.getSharedPreferences(PREF,Context.MODE_PRIVATE)
 fun all():List<TrackerProfile> { val raw=prefs.getString("profiles","[]")?:"[]"; val a=JSONArray(raw); return (0 until a.length()).map{val o=a.getJSONObject(it);TrackerProfile(o.getString("id"),o.getString("name"),o.optInt("worldLevel",0),o.optInt("ar",1),o.optString("region","Mondstadt"))} }
 fun save(p:TrackerProfile){saveAll(all().filterNot{it.id==p.id}+p)}
 fun delete(id:String){saveAll(all().filterNot{it.id==id})}
 private fun saveAll(ps:List<TrackerProfile>){val a=JSONArray();ps.forEach{p->a.put(JSONObject().put("id",p.id).put("name",p.name).put("worldLevel",p.worldLevel).put("ar",p.ar).put("region",p.region))};prefs.edit().putString("profiles",a.toString()).apply()}
}