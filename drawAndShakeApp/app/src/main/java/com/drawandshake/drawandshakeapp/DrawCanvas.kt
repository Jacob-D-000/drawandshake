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
    private var oldX: Float = 500f
    private var oldY: Float = 500f
    private val drawingCanvasId = activity.findViewById<ImageView>(R.id.drawingCanvas)
    private val displayMetrics = activity.resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val screenHeight = displayMetrics.heightPixels
    private val startingBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
    private var bitmap = startingBitmap // by default
    private val canvas = Canvas(bitmap)
    private val paint = Paint()

    open fun create(){
        this.canvas.drawColor(Color.WHITE)
        this.paint.color = Color.BLACK
        this.paint.strokeWidth = 10f
        this.drawingCanvasId.setImageBitmap(startingBitmap)
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
    fun getCanvas() : Canvas {
        return this.canvas
    }
    fun getBitMap() : Bitmap{
        return this.bitmap
    }

    fun getScreenWidth() : Int {
        return this.screenWidth;
    }

    fun getScreenHeight() : Int {
        return this.screenHeight;
    }
    fun resetBitMap() {
        this.drawingCanvasId.setImageBitmap(startingBitmap)
    }

    fun setBitMap(bitmap: Bitmap) {
        this.bitmap = bitmap;
    }

    fun getCanvasID() : ImageView {
        return this.drawingCanvasId
    }
    fun getPaint() : Paint {
        return this.paint;
    }
}