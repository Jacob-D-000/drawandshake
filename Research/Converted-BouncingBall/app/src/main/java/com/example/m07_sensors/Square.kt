package com.example.m07_sensors

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Square {
    var x = 200.0
    var y = 200.0
    var speedX = 10.0
    var speedY = 10.0
    var width = 100
    var height = 100
    var speed_resistance = 0.99 //amount of slow-down
    var acc_resistance = 0.99 //amount of slow-down
    private val bounds //used for Canvas.drawOval
            : RectF
    private val paint //paint style, color used for drawin
            : Paint
    private var ax = 0.0
    private var ay = 0.0
    private var az = 0.0 // Acceleration from different axis
    fun setAcc(ax: Double, ay: Double, az: Double) {
        this.ax = ax
        this.ay = ay
        this.az = az
    }

    init {
        bounds = RectF()
        paint = Paint()
        paint.color = Color.BLUE
    }

    fun draw(canvas: Canvas) {
        bounds[(x + width / 2).toFloat(), (y + height / 2).toFloat(), (x - width / 2).toFloat()] =
            (y - height / 2).toFloat()
        canvas.drawRect(bounds, paint)
    }

    fun moveWithCollisionDetection(box: Box) {
        // Get new (x,y) position
        x = x + speedX
        y = y + speedY

        // Add acceleration to speed
        speedX = speedX * speed_resistance + ax * acc_resistance
        speedY = speedY * speed_resistance + ay * acc_resistance

        // Detect collision and react
        if (x + width / 2 > box.xMax) {
            speedX = -speedX
            x = (box.xMax - width / 2).toDouble()
            cycleColor()
        } else if (x - width / 2 < box.xMin) {
            speedX = -speedX
            x = (box.xMin + width / 2).toDouble()
            cycleColor()
        }
        if (y + height / 2 > box.yMax) {
            speedY = -speedY
            y = (box.yMax - height / 2).toDouble()
            cycleColor()
        } else if (y - height / 2 < box.yMin) {
            speedY = -speedY
            y = (box.yMin + height / 2).toDouble()
            cycleColor()
        }
    }

    private fun cycleColor() {
        when (paint.color) {
            Color.BLUE -> paint.color = Color.RED
            Color.RED -> paint.color = Color.GREEN
            Color.GREEN -> paint.color = Color.BLUE
        }
    }
}