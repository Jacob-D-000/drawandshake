package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences


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
    private var firstPoint: Boolean = true;
    companion object {
        const val PREFS_NAME = "data" // Replace with your preference file name
        const val KEY_DATA = "Drawing" // Replace with your specific key
    }

    open fun create(){
        this.traceCanvas.drawColor(Color.WHITE)
        this.paint.color = Color.BLACK
        this.paint.strokeWidth = 10f
        this.drawingCanvasId.setImageBitmap(bitmap)
    }
    //read array to canvas
    @SuppressLint("CommitPrefEdits")
    fun save(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        getxyPoints().forEach { entry ->

            val existingData = sharedPreferences.getString(KEY_DATA, "")
            val appendedData = existingData + "${entry.key},${entry.value}\n"

            editor.putString(KEY_DATA, appendedData)
            editor.apply()
        }
        xyPoints.clear()
        firstPoint = true
    }
    //read file to canvas
    fun display(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val retrievedData = sharedPreferences.getString(
            KEY_DATA,
            ""
        )

        if(retrievedData != "") {

            val lines = retrievedData?.split("\n")
            if (lines != null) {
                for (line in lines) {
                    val points = line.split(",")
                    if (points.size == 2) {
                        if (points[0] == "new" && points[1] == "line") {
                            getxyPoints().forEach { entry ->
                                if (firstPoint) {
                                    setOldDrawX(entry.key)
                                    setOldDrawY(entry.value)
                                    firstPoint = false
                                }
                                this.getTraceCanvas().drawLine(
                                    getOldDrawX(),
                                    getOldDrawY(),
                                    entry.key,
                                    entry.value,
                                    getPaint()
                                )
                                this.getCanvasID().setImageBitmap(getBitMap())
                                setOldDrawX(entry.key)
                                setOldDrawY(entry.value)
                            }
                            xyPoints.clear()
                        } else {
                            val x = points[0].toFloat()
                            val y = points[1].toFloat()
                            xyPoints.put(x, y)
                        }
                    }
                }
            }
        }
    }
    fun newline(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(KEY_DATA, "new,line\n")
        editor.apply()
    }

    fun delete(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.clear()
        editor.apply()
        xyPoints.clear()
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
    fun getxyPoints() : MutableMap<Float,Float> {
        return this.xyPoints
    }
}
//MutableMap<Float, Float>