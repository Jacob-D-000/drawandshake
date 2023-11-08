package com.example.m07_sensors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by Russ on 08/04/2014.
 */
class Box(color: Int) {
    var xMin = 0
    var xMax = 0
    var yMin = 0
    var yMax = 0
    private val paint // paint style and color
: Paint
    private val bounds: Rect

    init {
        paint = Paint()
        paint.color = color
        bounds = Rect()
    }

    operator fun set(x: Int, y: Int, width: Int, height: Int) {
        xMin = x
        xMax = x + width - 1
        yMin = y
        yMax = y + height - 1
        // The box's bounds do not change unless the view's size changes
        bounds[xMin, yMin, xMax] = yMax
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(bounds, paint)
    }
}