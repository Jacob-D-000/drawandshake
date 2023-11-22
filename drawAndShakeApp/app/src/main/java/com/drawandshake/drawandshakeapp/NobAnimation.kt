package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.ImageButton
import kotlin.math.atan2

class NobAnimation(private var nob: ImageButton, private var nobD: ImageButton, private var nobDef: Float) {
    @SuppressLint("ClickableViewAccessibility")
    fun animation(){
        nobD.setOnTouchListener { _, motionEvent ->
            //Will happen when image is pressed other wise event is set to false
            when (motionEvent.action){
                //change opacity of nob direction image when user holds finger on image
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    nobD.alpha = 0.0f

                    val x: Float = motionEvent.x
                    val y: Float = motionEvent.y

                    val nobCenterX = nob.width / 2
                    val nobCenterY = nob.height / 2
                    val angle = Math.toDegrees(atan2(y - nobCenterY, x - nobCenterX).toDouble()) + nobDef //counteract preset angle for right nob

                    nob.rotation = angle.toFloat()
                }
                //when user lets go or the hold action is stopped
                // it will reset the opacity of the direction image
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    nobD.alpha = 1.0f
                    nob.rotation = 0f
                }
            }
            false
        }
    }
}