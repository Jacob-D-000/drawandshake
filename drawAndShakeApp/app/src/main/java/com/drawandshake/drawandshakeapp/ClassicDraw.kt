package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class ClassicDraw(private val activity: AppCompatActivity) : DrawCanvas(activity) {
    @SuppressLint("ClickableViewAccessibility")
    fun classicCanvas()
    {

        // Call NobAnimation to Rotate and setup touch listen.

        var oldLeftRotation : Float
        var oldRightRotation : Float
        var currentLeftRotation : Float
        var currentRightRotation : Float

        var currentX : Float;
        var currentY : Float;

        //NOTE THIS IS NOT THE NOB ANIMATION OBJECT, THIS IS JUST THE IMAGE
        val leftNob = activity.findViewById<ImageButton>(R.id.leftButton)
        val rightNob = activity.findViewById<ImageButton>(R.id.rightButton)



        //Starting the Thread to make drawing Loop
        val rotationThread = Thread()
        {
            // Turn off when you leave
            while (true)
            {
                //Thread.sleep(0)
                // This is only updated if the thread gets the chance to sleep. otherwise it reverts to first rotation.
                oldLeftRotation =  rotationCorrection(leftNob.rotation)
                oldRightRotation =  rotationCorrection(rightNob.rotation)
                Thread.sleep(100)

                currentLeftRotation =  rotationCorrection(leftNob.rotation)
                currentRightRotation =  rotationCorrection(rightNob.rotation)

                //Dumb idea, if the rotation is -180 to -0, going to subtract the absolute value of the rotation from 360 to fake 0-360 degs instead of using -180 to 180
//                oldLeftRotation = rotationCorrection(oldLeftRotation)
//                oldRightRotation = rotationCorrection(oldRightRotation)
//                currentLeftRotation = rotationCorrection(currentLeftRotation)
//                currentRightRotation = rotationCorrection(currentRightRotation)

                // println("oldRightRotation: " + oldRightRotation + "currentRightRotation: " + currentRightRotation )

                // Checking if positive rotation,
                if ( ( oldRightRotation - currentRightRotation )  > 5 )
                {
                    println("Up : " + (oldRightRotation - currentRightRotation) )
                    currentY = getOldDrawY() + 10
                }
                // Checking if negative, moving down if so
                else if ( (oldRightRotation - currentRightRotation )  < -5)
                {
                    println("Down: " + (oldRightRotation - currentRightRotation)  )
                    currentY = getOldDrawY() - 10
                }
                // No Change, not being touched
                else
                {
                    currentY = getOldDrawY()
                }

                // Checking if positive, moving right if so
                if ( (oldLeftRotation - currentLeftRotation) > 5 ) {
                    currentX = getOldDrawX() + 10
                    println("Right: " + (oldRightRotation - currentRightRotation) )
                }
                // Checking if negative, moving left if so
                else if ( ( oldLeftRotation - currentLeftRotation) < -5)
                {
                    println("Left: " + (oldRightRotation - currentRightRotation)  )
                    currentX = getOldDrawX() - 10
                }
                // No Change, not being touched
                else
                {
                    currentX = getOldDrawX()
                }

                //Draw Line
                this.getCanvas().drawLine(getOldDrawX(), getOldDrawY(), currentX, currentY, getPaint())
                this.getCanvasID().setImageBitmap(getBitMap())
                //this.getTraceCanvas().drawLine(getOldDrawX(), getOldDrawY(), x, y, getPaint())


                //Make Current the Draw
                setOldDrawX(currentX)
                setOldDrawY(currentY)
            }
        }
        // Let it run in the background, then start thread
        rotationThread.isDaemon = true
        rotationThread.start()
    }

    // Checks if the rotation negative, converting it to the positive equivalent rotation wise.
    // -179 becomes 181 | -90 becomes 270 | 90 remains 90  .
    private fun rotationCorrection(rotation : Float): Float {

        //For some reason, kotlin sees rotation as a val here, making a temp variable to fix it
        var updatedRotation = rotation

        if (rotation < 0)
        {
            updatedRotation = 360 - abs(rotation)
        }

        return updatedRotation
    }
}