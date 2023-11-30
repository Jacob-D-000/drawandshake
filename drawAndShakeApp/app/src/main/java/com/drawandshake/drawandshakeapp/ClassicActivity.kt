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

        val canvas = ClassicDraw(this)
        canvas.create()
        ShakeDetector(this, canvas.getTraceCanvas(), canvas.getBitMap(), canvas.getCanvasID()).start()

        val leftNob = NobAnimation(findViewById(R.id.leftButton),findViewById(R.id.leftButtonDirection), 0f)
        leftNob.animation()

        val rightNob = NobAnimation(findViewById(R.id.rightButton), findViewById(R.id.rightButtonDirection),90f)
        rightNob.animation()
        
//        canvas.classicCanvas(leftNob.getRotation(), rightNob.getRotation())
    }
}