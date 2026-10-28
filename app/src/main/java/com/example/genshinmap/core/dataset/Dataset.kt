package com.example.genshinmap.core.dataset
import com.example.genshinmap.core.database.MarkerEntity
import org.json.JSONArray
import org.json.JSONObject
object Dataset {
 fun parse(text:String):List<MarkerEntity> {
  val root=JSONObject(text); require(root.optInt("schemaVersion",-1)==1){"Unsupported dataset schema"}
  val items=root.optJSONArray("markers")?:JSONArray()
  return buildList { for(i in 0 until items.length()) { val m=items.getJSONObject(i); val id=m.getString("id"); val x=m.getDouble("x"); val y=m.getDouble("y"); require(x in 0.0..1.0 && y in 0.0..1.0){"Coordinates must be normalized"}; add(MarkerEntity(id,m.optString("name",id),m.optString("type","OTHER"),m.optString("region","Unknown"),x,y,m.optString("description",""))) } }
 }
}