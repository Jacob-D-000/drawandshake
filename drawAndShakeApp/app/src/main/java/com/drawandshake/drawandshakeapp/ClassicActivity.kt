package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.System.out
import kotlin.math.atan2

/**********
 * Liam Notes
 * Wow a week just to have chatGTP tell me three lines of code
 * 'good' SHIT android devs
 *
 * here i have the onCreate class for are classic drawing activity as well as the onStart
 * that is were the drawing and animations will go
 * I also call the Fucktion DrawBackArrow().backPressed() - i will go over that in the file its self
 */

class ClassicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic)
        System.out.println("X quor")
        System.out.println(findViewById<ImageButton>(R.id.leftButton).x)
        System.out.println(findViewById<ImageButton>(R.id.leftButton).width)
        System.out.println("Y quor")
        System.out.println(findViewById<ImageButton>(R.id.leftButton).y)
        System.out.println(findViewById<ImageButton>(R.id.leftButton).height)

    }
    //negate button press for both nobs
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onStart(){
        super.onStart()
        DrawBackArrowEvent(this).backPressed()

        findViewById<ImageButton>(R.id.leftButton).setOnTouchListener { _, motionEvent ->
            //Will happen when image is pressed other wise event is set to false
            when (motionEvent.action){
                //change opacity of nob direction image when user holds finger on image
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    findViewById<ImageView>(R.id.leftButtonDerection).alpha = 0.0f

                    val x: Float = motionEvent.x
                    val y: Float = motionEvent.y

                    val nobCenterX = (findViewById<ImageButton>(R.id.leftButton).x + findViewById<ImageButton>(R.id.leftButton).width) / 2
                    val nobCenterY = (findViewById<ImageButton>(R.id.leftButton).y + findViewById<ImageButton>(R.id.leftButton).height) / 2
                    val angle = Math.toDegrees(atan2(y - nobCenterY, x - nobCenterX).toDouble())

                    findViewById<ImageButton>(R.id.leftButton).rotation = angle.toFloat()
                    println(angle)
                }
                //when user lets go or the hold action is stopped
                // it will reset the opacity of the direction image
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    findViewById<ImageView>(R.id.leftButtonDerection).alpha = 1.0f
                    findViewById<ImageButton>(R.id.leftButton).rotation = 0f
                }
            }
            false
        }

        findViewById<ImageButton>(R.id.rightButton).setOnTouchListener { _, motionEvent ->
            //Will happen when image is pressed other wise event is set to false
            when (motionEvent.action){
                //change opacity of nob direction image when user holds finger on image
                MotionEvent.ACTION_DOWN -> {
                    findViewById<ImageView>(R.id.rightButtonDerection).alpha = 0.0f
                }
                //when user lets go or the hold action is stopped
                // it will reset the opacity of the direction image
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    findViewById<ImageView>(R.id.rightButtonDerection).alpha = 1.0f
                }
            }
            false
        }
    }
}