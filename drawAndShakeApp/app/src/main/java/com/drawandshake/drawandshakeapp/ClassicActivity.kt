package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
        // Initialize and start listening for shake events
//        ShakeDetector(this).start()
    }
    //negate button press for both nobs
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onStart(){
        super.onStart()
        DrawBackArrowEvent(this).backPressed()
        NobAnimation(findViewById<ImageButton>(R.id.leftButton),findViewById<ImageButton>(R.id.leftButtonDerection), 0f).animation()
        NobAnimation(findViewById<ImageButton>(R.id.rightButton), findViewById<ImageButton>(R.id.rightButtonDerection),90f).animation()
    }
    override fun onDestroy() {
        // Stop listening for shake events when the activity is destroyed
//        ShakeDetector(this).stop()
        super.onDestroy()
    }
}