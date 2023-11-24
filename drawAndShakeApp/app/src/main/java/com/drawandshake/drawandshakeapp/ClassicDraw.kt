package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ClassicDraw(private val activity: AppCompatActivity) : DrawCanvas(activity) {
    @SuppressLint("ClickableViewAccessibility")
    fun classicCanvas(rotation: Float, firstTime: Boolean, derection: Boolean)
    {

        // Call NobAnimation to Rotate and setup touch listen.

        var oldLeftRotation = 0.0f // This will be overwritten on first time left
        var oldRightRotation = 0.0f // This will be overwritten on first time right
        var currentLeftRotation : Float
        var currentRightRotation : Float

        var currentX = getOldDrawX()
        var currentY = getOldDrawY()



            // Checking for the initial touch either side is touched, to prevent massive streaks on first touch
        if (firstTime){
            oldLeftRotation = rotation
        }

            currentLeftRotation = leftNob.getRotation()
            currentRightRotation = rightNob.getRotation()

            // Checking for Up or Down
            if ((currentRightRotation - oldRightRotation) > 0) {
                currentX = getOldDrawX() + 10
            } else {
                currentX = getOldDrawX() - 10
            }

            // Checking Left/Right
            if ((currentLeftRotation - oldLeftRotation) > 0) {
                currentY = getOldDrawY() + 10
            } else {
                currentY = getOldDrawY() - 10
            }

            //Draw Line
            this.getCanvas().drawLine(getOldDrawX(), getOldDrawY(), currentX, currentY, getPaint())
            this.getCanvasID().setImageBitmap(getBitMap())
            //this.getTraceCanvas().drawLine(getOldDrawX(), getOldDrawY(), x, y, getPaint())


            //Make Current the Draw
            setOldDrawX(currentX)
            setOldDrawY(currentY)
        }


        // end of while view is open

//        println("Left Nob Rotation Value: "  + leftNob.rotation)
//        println("Right Nob Rotation Value: "  + rightNob.rotation)



        //default postion
        // get rotation value from nob animation
        //
    }


}