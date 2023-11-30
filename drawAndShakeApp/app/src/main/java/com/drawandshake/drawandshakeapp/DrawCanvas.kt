package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

open class DrawCanvas(activity: AppCompatActivity) {
    private var oldX: Float = 0f
    private var oldY: Float = 0f
    private var xyPoints = mutableMapOf<Float,Float>()
    private val drawingCanvasId = activity.findViewById<ImageView>(R.id.drawingCanvas)
    private val displayMetrics = activity.resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val screenHeight = displayMetrics.heightPixels
    private val bitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
    private val traceCanvas = Canvas(bitmap)
    private val paint = Paint()

    open fun create(){
        this.traceCanvas.drawColor(Color.WHITE)
        this.paint.color = Color.BLACK
        this.paint.strokeWidth = 10f
        this.drawingCanvasId.setImageBitmap(bitmap)
    }
    //read array to canvas
    fun save(){

    }
    //read file to canvas
    fun display(){

    }
    fun getOldDrawX() : Float {
        return this.oldX
    }
    fun setOldDrawX(x : Float) {
        this.oldX = x
    }
    fun getOldDrawY() : Float {
        return this.oldY
    }
    fun setOldDrawY(y : Float) {
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
    fun getPaint() : Paint {
        return this.paint;
    }
    fun setxyPoints(x: Float,y: Float){
        this.xyPoints.put(x,y)
    }
    fun getxyPoints() : Int {
        return this.xyPoints.size
    }
}
//MutableMap<Float, Float>