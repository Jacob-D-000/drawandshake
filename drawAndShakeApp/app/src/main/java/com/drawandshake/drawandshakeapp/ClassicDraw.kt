package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ClassicDraw(private val activity: AppCompatActivity) : DrawCanvas(activity) {
    @SuppressLint("ClickableViewAccessibility")
    fun classicCanvas(leftRotation : Float, rightRotation : Float)
    {
        // leftButtonDirection is the image that DISAPPEARS when held
        // leftButton is the image that ROTATES when held
        val leftNob = activity.findViewById<ImageButton>(R.id.leftButton)
        val rightNob = activity.findViewById<ImageButton>(R.id.rightButton)

        println("Left Nob Rotation Value: "  + leftNob.rotation)
        println("Right Nob Rotation Value: "  + rightNob.rotation)


        //default postion
        // get rotation value from nob animation
        //
    }
}