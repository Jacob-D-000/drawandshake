package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
        DrawBackArrowEvent(this).backPressed()
    }
    //negate button press for both nobs
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onStart(){
        super.onStart()

<<<<<<< Updated upstream
        val canvas = ClassicDraw(this)
        canvas.create()
        ShakeDetector(this, canvas.getTraceCanvas(), canvas.getBitMap(), canvas.getCanvasID()).start()

        val leftNob = NobAnimation(findViewById(R.id.leftButton),findViewById(R.id.leftButtonDirection), 0f)
        leftNob.animation()

        val rightNob = NobAnimation(findViewById(R.id.rightButton), findViewById(R.id.rightButtonDirection),90f)
        rightNob.animation()
        
//        canvas.classicCanvas(leftNob.getRotation(), rightNob.getRotation())
=======
        findViewById<ImageButton>(R.id.leftButton).setOnTouchListener { _, motionEvent ->
            //Will happen when image is pressed other wise event is set to false
            when (motionEvent.action){
                //change opacity of nob direction image when user holds finger on image
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {

                    //remove directional image (not the image moving)
                    findViewById<ImageView>(R.id.leftButtonDerection).alpha = 0.0f

                    //get y and x value of finger
                    val x: Float = motionEvent.x
                    val y: Float = motionEvent.y

                    //find x and y value for nob image (this is where I think the problem is)
                    val nobCenterX = (findViewById<ImageButton>(R.id.leftButton).x + findViewById<ImageButton>(R.id.leftButton).width) / 2
                    val nobCenterY = (findViewById<ImageButton>(R.id.leftButton).y + findViewById<ImageButton>(R.id.leftButton).height) / 2

                    //calculate angle from finger to nob point
                    val angle = Math.toDegrees(atan2(y - nobCenterY, x - nobCenterX).toDouble())

                    //apply angle to nob image rotation
                    findViewById<ImageButton>(R.id.leftButton).rotation = angle.toFloat()
                }
                //when user lets go or the hold action is stopped
                //it will reset the opacity of the direction image
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
>>>>>>> Stashed changes
    }
}