package com.drawandshake.drawandshakeapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.os.Bundle
import android.util.DisplayMetrics
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
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        // Initialize and start listening for shake events (Liam's Code)
        ShakeDetector(this).start()

        val drawingCanvasId = findViewById<ImageView>(R.id.drawingCanvas)

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        println("Width: $screenWidth")
        println("Height: $screenHeight")


        val bitmap: Bitmap = Bitmap.createBitmap(screenWidth, screenHeight,Bitmap.Config.ARGB_8888)
        val traceCanvas = Canvas(bitmap)

        // Example: Draw a red circle on the Canvas
        val paint = Paint()
        paint.color = Color.RED
        traceCanvas.drawCircle(250f, 250f, 100f, paint)
        traceCanvas.drawCircle(1000f, 1000f, 10f, paint)

        drawingCanvasId.setImageBitmap(bitmap)

    }
    
    override fun onStart()
    {
        super.onStart()
        DrawBackArrowEvent(this).backPressed()
        println("Height: " + findViewById<ImageView>(R.id.drawingCanvas).height)
        println("Width: " + findViewById<ImageView>(R.id.drawingCanvas).width)
    }
    
    // Liams code 
    override fun onDestroy() {
        // Stop listening for shake events when the activity is destroyed
        ShakeDetector(this).stop()
        super.onDestroy()
    }
}