package com.drawandshake.drawandshakeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TraceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        // Initialize and start listening for shake events (Liam's Code)
    }

    override fun onStart()
    {
        super.onStart()
        val canvas = TraceDraw(this)
        DrawBackArrowEvent(this, canvas).backPressed()
        ShakeDetector(this, this, canvas, canvas.getCanvas(), canvas.getBitMap(), canvas.getCanvasID()).start()
        canvas.create()
        canvas.traceCanvas()
    }
}