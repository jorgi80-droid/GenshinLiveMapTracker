package com.example.genshinmap.core.map

import com.example.genshinmap.core.model.MapPoint

object CoordinateTransform {
    fun normalizedToPixels(point: MapPoint, width: Float, height: Float): Pair<Float, Float> =
        (point.x.coerceIn(0f, 1f) * width) to (point.y.coerceIn(0f, 1f) * height)
}
