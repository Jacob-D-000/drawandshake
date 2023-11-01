package com.drawandshake.drawandshakeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Canvas

/**********
 * Liam Notes
 * Idk what to say this time its just really really REALlYY REALLYYYYYY shity shit language
 *
 * here i have the onCreate class for are Trace drawing activity as well as the onStart
 * that is were the drawing and finger detection will go
 * I also call the Fucktion DrawBackArrow().backPressed() here as well - i will go over that in the file its self
 */
class TraceActivity : AppCompatActivity() {

//    This fucntion will be called when the file first draws the canvas to the screen
//    override fun onDraw(screen : Canvas) {
//
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)
//      Make the screen a canvas


//      If structure to determine if a file exist to pull points from to create lines. These would be in a text file formatted at arrays with four values
    }
    override fun onStart(){
        super.onStart()
        DrawBackArrowEvent(this).backPressed()
        val screen =  Canvas()

    }
}