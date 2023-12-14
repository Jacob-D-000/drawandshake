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
    private var oldX: Float = 500f
    private var oldY: Float = 500f
    private var xyPoints = arrayListOf<Array<Float>>()
    private val drawingCanvasId = activity.findViewById<ImageView>(R.id.drawingCanvas)
    private val displayMetrics = activity.resources.displayMetrics
    private val screenWidth = displayMetrics.widthPixels
    private val screenHeight = displayMetrics.heightPixels
    private val startingBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
    private var bitmap = startingBitmap // by default
    private val canvas = Canvas(bitmap)
    private val paint = Paint()
    private var firstPoint: Boolean = true;
    companion object {
        const val PREFS_NAME = "data" // Replace with your preference file name
        const val KEY_DATA = "Drawing" // Replace with your specific key
    }

    open fun create(){
        this.canvas.drawColor(Color.WHITE)
        this.paint.color = Color.BLACK
        this.paint.strokeWidth = 10f
        this.drawingCanvasId.setImageBitmap(startingBitmap)
    }
    //read array to canvas
    @SuppressLint("CommitPrefEdits")
    fun save(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        getxyPoints().forEach { entry ->

            val existingData = sharedPreferences.getString(KEY_DATA, "")
            val appendedData = existingData + "${entry[0]},${entry[1]}\n"

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
                        val x = points[0].toFloat()
                        val y = points[1].toFloat()
                        xyPoints.add(arrayOf(x,y))
                    }
                }
            }
            getxyPoints().forEach { entry ->
                if (entry[0] == 0f)
                {
                    firstPoint = true;
                }
                //if not empty line delimiter do this
                else  {
                    if (firstPoint) {
                        setOldDrawX(entry[0])
                        setOldDrawY(entry[1])
                        firstPoint = false
                    }
                    this.getCanvas().drawLine(
                        getOldDrawX(),
                        getOldDrawY(),
                        entry[0],
                        entry[1],
                        getPaint()
                    )
                        setOldDrawX(entry[0])
                        setOldDrawY(entry[1])
                }
                this.getCanvasID().setImageBitmap(getBitMap())
            }
        }
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
    fun setxyPoints(x: Float,y: Float){
        this.xyPoints.add(arrayOf(x,y))
    }
    fun getxyPoints() : ArrayList<Array<Float>> {
        return this.xyPoints
    }
}