package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TraceDraw(private val activity: AppCompatActivity) : DrawCanvas(activity) {
    @SuppressLint("ClickableViewAccessibility")
    fun traceCanvas(){
        //x,y
        //val xyPoints = mutableMapOf<Float,Float>()
        var firstTime = true
        display(this.activity)
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

                    setxyPoints(x,y)

                    this.getTraceCanvas().drawLine(getOldDrawX(), getOldDrawY(), x, y, getPaint())
                    this.getCanvasID().setImageBitmap(getBitMap())
                    setOldDrawX(x)
                    setOldDrawY(y)
                }
                MotionEvent.ACTION_UP -> {
                    setxyPoints(0f,0f)

                    firstTime = true
                }
            }
            true
        }
    }
}