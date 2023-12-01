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
    }
    //read file to canvas
    fun display(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val retrievedData = sharedPreferences.getString(
            KEY_DATA,
            ""
        )
        val inputString = "1,2\n3,4\n5,6"

//// Split the string into lines
//        val lines = inputString.split("\n")
//
//// Process each line and split into individual numbers
//        val resultList = mutableListOf<Pair<Int, Int>>()
//
//        for (line in lines) {
//            val numbers = line.split(",")
//            if (numbers.size == 2) {
//                val num1 = numbers[0].toInt()
//                val num2 = numbers[1].toInt()
//                resultList.add(num1 to num2)
//            }
//        }
//
//// Now resultList contains pairs of numbers
//        for (pair in resultList) {
//            println("Number Pair: $pair")
//        }
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