package com.drawandshake.drawandshakeapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

/***************
 * Nathan and Jacob PLZ read as this activity's are important and make this shit much more understandable
 * also go to the AndroidManifest.xml file to see how activity's are established in the app
 *
 * Liam Notes
 * First and foremost FU$%# SH($ language F%^$ Kotlin
 * anyway
 * this is the main activity file
 *
 * in this file the app is made starting with the activity_main.xml layout file
 * the contents of this activity allow the user to select a drawing mode
 * they can also start the drawing activity
 */
open class MainActivity : AppCompatActivity() {
    /***
     * onCreate
     * the onCreate function is in the start of EVERY new activity
     * you guys probably don't need to make any more as if have made 3 (Trace, Classic, and main)
     * however this is where the xml layout and in are case save states will be checked and instantiated/created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * this is the onStart function as implied this is were most of the logic and loops are created/run
     * so this would be were the drawing and writing to a file will happen
     * I will aso go over what this code is doing
     * I create the animation for the mode select switch by see if it is one of of and then
     * changing the opacity of the corresponding image use a event listener
     * I also use a event listener to check if the draw button is pressed
     * thin based on the state of the switch i load the corresponding activity
     */
    override fun onStart(){
        super.onStart()
        //boolean to track switch state
        //set classic image opacity as trace is selected on start
        var drawState = false
        findViewById<ImageView>(R.id.Classic_Image).imageAlpha = 100

        //switch event listener
        findViewById<SwitchCompat>(R.id.menu_switch).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                drawState = true
                findViewById<ImageView>(R.id.Classic_Image).imageAlpha = 255
                findViewById<ImageView>(R.id.Trace_Image).imageAlpha = 100
            } else {
                drawState = false
                findViewById<ImageView>(R.id.Classic_Image).imageAlpha = 100
                findViewById<ImageView>(R.id.Trace_Image).imageAlpha = 255
            }
        }

        //draw button event listener
        findViewById<ImageButton>(R.id.DrawButton).setOnClickListener {
            if(!drawState){
                /**
                 * here we use java in kotlin WOOOW
                 * so what this is doing is making a new activity(trace or classic)
                 * we initialize it then start it
                 */
                val intent = Intent(this, TraceActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, ClassicActivity::class.java)
                startActivity(intent)
            }
        }
    }
}