package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ClassicActivity : AppCompatActivity() {
    companion object {
        var active = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic)
    }

    //negate button press for both nobs
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onStart(){
        super.onStart()
        active = true
        val canvas = ClassicDraw(this)
        canvas.setActiveState(true)
        DrawBackArrowEvent(this, canvas).backPressed()
        canvas.create()
        ShakeDetector(this, this, canvas, canvas.getCanvas(), canvas.getBitMap(), canvas.getCanvasID()).start()

        val leftNob = NobAnimation(findViewById(R.id.leftButton), findViewById(R.id.leftButtonDirection), 0f, false, canvas)
        leftNob.animation()

        val rightNob = NobAnimation(findViewById(R.id.rightButton), findViewById(R.id.rightButtonDirection),0f, false, canvas)
        rightNob.animation()
        canvas.classicCanvas()
    }
    override fun onStop() {
        super.onStop()
        ClassicDraw(this).setActiveState(false)
    }
}