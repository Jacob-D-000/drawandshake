package com.drawandshake.drawandshakeapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2

/**********
 * Liam Notes
 * Idk what to say this time its just really really REALlYY REALLYYYYYY shity shit language
 *
 * here i have the onCreate class for are Trace drawing activity as well as the onStart
 * that is were the drawing and finger detection will go
 * I also call the Fucktion DrawBackArrow().backPressed() here as well - i will go over that in the file its self
 */
class TraceActivity : AppCompatActivity() {

    var oldX = 0f

    var oldY = 0f
    fun getOldDrawX() : Float {
        return oldX
    }

    fun setOldDrawX(x : Float) {
        this.oldX = x
    }

    fun getOldDrawY() : Float {
        return oldY;
    }

    fun setOldDrawY(y : Float) {
        this.oldY = y
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        // Initialize and start listening for shake events (Liam's Code)


    }
    
    override fun onStart()
    {
        super.onStart()
        DrawBackArrowEvent(this).backPressed()

        val drawingCanvasId = findViewById<ImageView>(R.id.drawingCanvas)

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        println("Width: $screenWidth")
        println("Height: $screenHeight")

        val bitmap: Bitmap = Bitmap.createBitmap(screenWidth, screenHeight,Bitmap.Config.ARGB_8888)
        val traceCanvas = Canvas(bitmap)

        ShakeDetector(this, traceCanvas).start()

        // Example: Draw a red circle on the Canvas
        val paint = Paint()
        paint.color = Color.RED
//        traceCanvas.drawCircle(250f, 250f, 100f, paint)
//        traceCanvas.drawCircle(1000f, 1000f, 10f, paint)

        drawingCanvasId.setImageBitmap(bitmap)

        var firstTime : Boolean = true


        findViewById<ImageView>(R.id.drawingCanvas).setOnTouchListener { _, motionEvent ->

            when (motionEvent.action)
            {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {


                    if (firstTime)
                     {
                        println("FIRST TIME TOUCH DOWN")
                        setOldDrawX(motionEvent.x)
                        setOldDrawY(motionEvent.y)
                        firstTime = false
                    }
                    
                    var x = motionEvent.x
                    var y = motionEvent.y

                    traceCanvas.drawLine(getOldDrawX(), getOldDrawY(), x, y, paint)
//                    traceCanvas.drawLine(prevX,prevY,x,y)
                    drawingCanvasId.setImageBitmap(bitmap)
                    setOldDrawX(x)
                    setOldDrawY(y)
//
//                    println(prevX + ": " + prevX)
//                    println(prevY + ": " + prevY)
                }
                MotionEvent.ACTION_UP -> {
                    firstTime = true
                    traceCanvas.drawColor(Color.WHITE)
                }
            }
            true
        }

    }
    
    // Liams code 
    override fun onDestroy() {
        // Stop listening for shake events when the activity is destroyed

        super.onDestroy()
    }
}