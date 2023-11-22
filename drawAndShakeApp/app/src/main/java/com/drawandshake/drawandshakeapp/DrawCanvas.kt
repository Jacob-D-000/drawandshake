package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DrawCanvas(private val activity: AppCompatActivity) {
    private var oldX: Float = 0f
    private var oldY: Float = 0f
    private val drawingCanvasId = activity.findViewById<ImageView>(R.id.drawingCanvas)
    private val displayMetrics = activity.resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val screenHeight = displayMetrics.heightPixels
    private val bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
    private val traceCanvas = Canvas(bitmap)
    private val paint = Paint()

    fun create(){
        this.traceCanvas.drawColor(Color.WHITE)
        this.paint.color = Color.BLACK
        this.paint.strokeWidth = 10f
        this.drawingCanvasId.setImageBitmap(bitmap)
    }
    @SuppressLint("ClickableViewAccessibility")
    fun traceCanvas() {
        var firstTime = true
        this.activity.findViewById<ImageView>(R.id.drawingCanvas).setOnTouchListener { _, motionEvent ->

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

                    val x = motionEvent.x
                    val y = motionEvent.y

                    this.traceCanvas.drawLine(getOldDrawX(), getOldDrawY(), x, y, paint)
                    this.drawingCanvasId.setImageBitmap(bitmap)
                    setOldDrawX(x)
                    setOldDrawY(y)
                }
                MotionEvent.ACTION_UP -> {
                    firstTime = true
                }
            }
            true
        }
    }

    fun classicCanvas(){

    }
    private fun getOldDrawX() : Float {
        return this.oldX
    }
    private fun setOldDrawX(x : Float) {
        this.oldX = x
    }
    private fun getOldDrawY() : Float {
        return this.oldY
    }
    private fun setOldDrawY(y : Float) {
        this.oldY = y
    }
    fun getTraceCanvas() : Canvas {
        return this.traceCanvas
    }
    fun getBitMap() : Bitmap{
        return this.bitmap
    }
    fun getCanvasID() : ImageView {
        return this.drawingCanvasId
    }

}