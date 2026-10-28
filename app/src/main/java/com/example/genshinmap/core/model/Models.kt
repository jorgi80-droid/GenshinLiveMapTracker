package com.example.genshinmap.core.model
enum class CollectionStatus { UNKNOWN, NOT_COLLECTED, COLLECTED }
enum class MarkerType { OCULUS, RESOURCE, ENEMY, CHEST, WAYPOINT, OTHER }
data class MapPoint(val x:Float,val y:Float)
data class PlayerPosition(val x:Float,val y:Float,val confidence:Float=0f,val source:String="MANUAL")