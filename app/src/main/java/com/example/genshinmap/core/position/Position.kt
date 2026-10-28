package com.example.genshinmap.core.position
import com.example.genshinmap.core.model.PlayerPosition
sealed interface PositionResult { data class Available(val position:PlayerPosition):PositionResult; data class Unavailable(val reason:String):PositionResult }
/** Manual companion position; does not inspect game memory or inject into the game. */
object Position { fun manual(x:Float,y:Float):PositionResult=if(x in 0f..1f&&y in 0f..1f) PositionResult.Available(PlayerPosition(x,y,1f,"MANUAL")) else PositionResult.Unavailable("Coordinates must be between 0 and 1") }