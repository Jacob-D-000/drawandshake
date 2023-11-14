package com.example.m07_sensors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import java.util.Random

/**
 * Created by Russ on 08/04/2014.
 */
class Ball {
    var radius = 50.0 // Ball's radius
    var x // Ball's center (x,y)
            : Double
    var y: Double
    var speedX // Ball's speed
            : Double
    var speedY: Double
    var speed_resistance = 0.99 //amount of slow-down
    var acc_resistance = 0.99 //amount of slow-down
    private var bounds // Needed for Canvas.drawOval
            : RectF
    private var paint // The paint style, color used for drawing
            : Paint

    // Add Acceleration
    private var ax = 0.0
    private var ay = 0.0
    private var az = 0.0 // Acceleration from different axis
    fun setAcc(ax: Double, ay: Double, az: Double) {
        this.ax = ax
        this.ay = ay
        this.az = az
    }

    var r = Random() // seed random number generator

    // Constructor
    constructor(color: Int) {
        bounds = RectF()
        paint = Paint()
        paint.color = color

        // random position and speed
        x = radius + r.nextInt(800)
        y = radius + r.nextInt(800)
        speedX = (r.nextInt(10) - 5).toDouble()
        speedY = (r.nextInt(10) - 5).toDouble()
    }

    // Constructor
    constructor(color: Int, x: Float, y: Float, speedX: Float, speedY: Float) {
        bounds = RectF()
        paint = Paint()
        paint.color = color

        // use parameter position and speed
        this.x = x.toDouble()
        this.y = y.toDouble()
        this.speedX = speedX.toDouble()
        this.speedY = speedY.toDouble()
    }

    fun moveWithCollisionDetection(box: Box) {
        // Get new (x,y) position
        x = x + speedX
        y = y + speedY

        // Add acceleration to speed
        speedX = speedX * speed_resistance + ax * acc_resistance
        speedY = speedY * speed_resistance + ay * acc_resistance

        // Detect collision and react
        if (x + radius > box.xMax) {
            speedX = -speedX
            x = box.xMax - radius
        } else if (x - radius < box.xMin) {
            speedX = -speedX
            x = box.xMin + radius
        }
        if (y + radius > box.yMax) {
            speedY = -speedY
            y = box.yMax - radius
        } else if (y - radius < box.yMin) {
            speedY = -speedY
            y = box.yMin + radius
        }
    }

    fun draw(canvas: Canvas) {
        // convert to float for bounds
        bounds[(x - radius).toFloat(), (y - radius).toFloat(), (x + radius).toFloat()] =
            (y + radius).toFloat()
        canvas.drawOval(bounds, paint)
    }
}