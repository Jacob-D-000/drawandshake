package com.drawandshake.drawandshakeapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.os.Bundle
import android.widget.ImageView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        val drawingCanvasId = findViewById<ImageView>(R.id.drawingCanvas)

        // These are what I want to include in the createBitmap below
        // The problem is they always return 0, not the filled screen size
        println(drawingCanvasId.height)
        println(drawingCanvasId.width)


        val bitmap: Bitmap = Bitmap.createBitmap(500, 500,Bitmap.Config.ARGB_8888)
        val traceCanvas = Canvas(bitmap)

        // Example: Draw a red circle on the Canvas
        val paint = Paint()
        paint.color = Color.RED
        traceCanvas.drawCircle(250f, 250f, 100f, paint)
        
        drawingCanvasId.setImageBitmap(bitmap)
    }

    override fun onStart(){
        super.onStart()
        DrawBackArrowEvent(this).backPressed()
    }
}