package com.drawandshake.drawandshakeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**********
 * Liam Notes
 * Idk what to say this time its just really really REALlYY REALLYYYYYY shity shit language
 *
 * here i have the onCreate class for are Trace drawing activity as well as the onStart
 * that is were the drawing and finger detection will go
 * I also call the Fucktion DrawBackArrow().backPressed() here as well - i will go over that in the file its self
 */
class TraceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)
        DrawBackArrowEvent(this).backPressed()
        // Initialize and start listening for shake events (Liam's Code)
    }

    override fun onStart()
    {
        super.onStart()
        val canvas = TraceDraw(this)
        ShakeDetector(this, canvas.getTraceCanvas(), canvas.getBitMap(), canvas.getCanvasID()).start()
        canvas.create()
        canvas.traceCanvas()
    }
}