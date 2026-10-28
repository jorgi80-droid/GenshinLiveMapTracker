package com.example.genshinmap.core.domain
import com.example.genshinmap.core.database.CollectionStateEntity
import com.example.genshinmap.core.database.MarkerEntity
object Domain {
 fun progress(markers:List<MarkerEntity>,states:List<CollectionStateEntity>):Float {
  if(markers.isEmpty()) return 0f
  val ids=markers.map{it.id}.toSet()
  val collected=states.count{it.status=="COLLECTED" && it.markerId in ids}
  return collected.toFloat()/markers.size
 }
 fun filter(markers:List<MarkerEntity>,region:String?,type:String?,query:String)=markers.filter{m->(region==null||m.region==region)&&(type==null||m.type==type)&&(query.isBlank()||m.name.contains(query,true)||m.description.contains(query,true))}
 fun nextStatus(current:String)=when(current){"UNKNOWN"->"NOT_COLLECTED";"NOT_COLLECTED"->"COLLECTED";"COLLECTED"->"UNKNOWN";else->"NOT_COLLECTED"}
}