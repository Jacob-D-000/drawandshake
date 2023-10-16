package com.drawandshake.drawandshakeapp

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
    override fun onStart(){
        super.onStart()
    }
}