package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import kotlin.math.abs

class ClassicDraw(private val activity: AppCompatActivity) : DrawCanvas(activity) {

    private var rotationThread: Thread? = null
    private val shakyHandsDeadzone = 3;
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

        //Starting the Thread to make drawing Loop | Only create if it does not exist yet
        // this.getCanvas().setBitmap(getBitMap())
        if (this.rotationThread == null)
        {
            this.rotationThread = Thread()
            {
                // TODO: Turn off when you leave this activity
                while (true) {
                    //Dumb idea, if the rotation of the image is between -180 to -0 degrees.
                    //subtract the absolute value of the rotation from 360 to transform them into
                    // 180-359 degrees.  Instead of using -180 to 180, just use 0 to 360
                    oldLeftRotation = rotationCorrection(leftNob.rotation)
                    oldRightRotation = rotationCorrection(rightNob.rotation)
                    Thread.sleep(100)
                    currentLeftRotation = rotationCorrection(leftNob.rotation)
                    currentRightRotation = rotationCorrection(rightNob.rotation)

                    // Checking if positive rotation, | 3 instead of 0 for shaky hands
                    if ((oldRightRotation - currentRightRotation) > shakyHandsDeadzone) {
                        // println("Up : " + (oldRightRotation - currentRightRotation) )
                        currentY = getOldDrawY() + 10
                    }
                    // Checking if negative, moving down if so | -3 instead of 0 for shaky hands
                    else if ((oldRightRotation - currentRightRotation) < -shakyHandsDeadzone) {
                        // println("Down: " + (oldRightRotation - currentRightRotation)  )
                        currentY = getOldDrawY() - 10
                    }
                    // No Change, not being touched
                    else {
                        currentY = getOldDrawY()
                    }

                    // Checking if positive, moving RIGHT if so | 3 instead of 0 for shaky hands
                    if ((oldLeftRotation - currentLeftRotation) > shakyHandsDeadzone) {
                        currentX = getOldDrawX() - 10
                        // println("Right: " + (oldRightRotation - currentRightRotation) )
                    }
                    // Checking if negative, moving LEFT if so. | -3 instead of 0 for shaky hands
                    else if ((oldLeftRotation - currentLeftRotation) < -shakyHandsDeadzone) {
                        //println("Left: " + (oldRightRotation - currentRightRotation))
                        currentX = getOldDrawX() + 10
                    }
                    // No Change, not being touched
                    else {
                        currentX = getOldDrawX()
                    }

                    //Draw Line
                    this.getCanvas().drawLine(getOldDrawX(), getOldDrawY(), currentX, currentY, getPaint())
                    this.getCanvasID().setImageBitmap(getBitMap())

                    // Move this to on close for final version, using this to test | BROKEN
                    // this.setBitMap(getBitMap())

                    //Make Current the Draw
                    setOldDrawX(currentX)
                    setOldDrawY(currentY)
                }
            }
            // Let it run in the background, then start thread
            rotationThread?.isDaemon = true
            rotationThread?.start()
        }
        // TODO: THIS NEVER RUNS, EVEN IF THE THREAD ALREADY EXISTS
        else { println("Thread Already Exists")}
    }


    // Checks if the rotation negative, converting it to the positive equivalent rotation wise.
    // -179 becomes 181 | -90 becomes 270 | 90 remains 90  .
    private fun rotationCorrection(rotation : Float): Float
    {

        //For some reason, kotlin sees rotation as a val here, making a temp variable to fix it
        var updatedRotation = rotation

        if (rotation < 0)
        {
            updatedRotation = 360 - abs(rotation)
        }

        return updatedRotation
    }
}