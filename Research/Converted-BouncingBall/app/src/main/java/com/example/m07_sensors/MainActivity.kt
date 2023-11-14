package com.example.m07_sensors

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View

/**
 * Revised Bouncing Ball example.  Chopped away
 * as much as possible, those bits not needed
 * other than to support old Android versions.
 */
class MainActivity : Activity() {
    private var bouncingBallView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main_delete);

        // Add Bouncing Ball
        // http://www3.ntu.edu.sg/home/ehchua/programming/android/Android_2D.html
        bouncingBallView = BouncingBallView(this)
        setContentView(bouncingBallView)
        Log.v("SENSORS", "onCreate bouncingBallView=" + bouncingBallView.toString())


        //Check sensors
        setupSensors()
    }

    override fun onResume() {
        super.onResume()
        if (bouncingBallView != null) {
            Log.v("SENSORS", "onResume bouncingBallView=" + bouncingBallView.toString())
            if (my_Sensor != null) {
                Log.v("SENSORS", "onResume my_Sensor=" + my_Sensor.toString())
                mSensorManager!!.registerListener(
                    bouncingBallView as SensorEventListener?,
                    my_Sensor,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        } else {
            Log.v("SENSORS", "onResume bouncingBallView=null")
        }
        Log.v("SENSORS", "onResume ACCELLEROMETER")
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(bouncingBallView as SensorEventListener?)
        Log.v("SENSORS", "onPause ACCELLEROMETER")
    }

    // Sensors
    private var mSensorManager: SensorManager? = null
    private var my_Sensor: Sensor? = null
    private fun setupSensors() {
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val deviceSensors = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)
        Log.v("SENSORS", "Sensor List=$deviceSensors")

        // Use the accelerometer.
        if (mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            my_Sensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            //my_Sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            Log.v("SENSORS", "my_Sensor=" + my_Sensor.toString())
        } else {
            // Sorry, there are no accelerometers on your device.
            // You can't play this game.
            Log.v("SENSORS", "NO SENSOR TYPE?")
        }
    }
}