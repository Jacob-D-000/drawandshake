package com.drawandshake.drawandshakeapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import android.widget.ImageView
import kotlin.math.sqrt

class ShakeDetector(context : Context, private val canvas: Canvas, private val bitmap : Bitmap, private val view : View) : SensorEventListener{
    //make sesor listiner and manager
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    //set vars
    private var lastTime: Long = 0
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var lastZ: Float = 0f

    init {
        // Initialize the SensorManager and accelerometer sensor
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    fun start() {
        // Register the accelerometer sensor listener
        accelerometer?.also { accelSensor ->
            sensorManager?.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //need to overide if overiding senser change IDK why
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Check if the event is from the accelerometer
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val currentTime = System.currentTimeMillis()

            // Calculate time difference since the last event
            val timeDifference = currentTime - lastTime

            // Check if enough time has passed since the last event
            if (timeDifference > SHAKE_THRESHOLD_TIME) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                // Calculate movement difference
                val deltaX = x - lastX
                val deltaY = y - lastY
                val deltaZ = z - lastZ

                // Calculate total movement
                val totalMovement = sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ).toDouble())

                // Check if the total movement is above the threshold
                if (totalMovement > SHAKE_THRESHOLD) {
                    //delet function gose here
                    println("Shake detected!")
                    canvas.drawColor(Color.WHITE)
                    view.findViewById<ImageView>(R.id.drawingCanvas).setImageBitmap(bitmap)
                }

                // Update last values and time
                lastX = x
                lastY = y
                lastZ = z
                lastTime = currentTime
            }
        }
    }
    companion object {
        //may need to be changed dont now oh sensitive it is
        // Threshold values for shake detection
        private const val SHAKE_THRESHOLD = 30.0
        private const val SHAKE_THRESHOLD_TIME = 100 // in milliseconds
    }
}