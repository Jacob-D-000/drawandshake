package com.example.rotationknobmockup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onStart()
    {
        super.onStart()

        var currentRotation = 0.0f

        findViewById<ImageView>(R.id.rotationKnob).rotation = currentRotation

        //R.id.rotationKnob
        findViewById<ImageView>(R.id.rotationKnob).setOnClickListener()
        {
            currentRotation += 45.0f
            findViewById<ImageView>(R.id.rotationKnob).rotation = currentRotation
        }
    }
}