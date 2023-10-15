package com.drawandshake.drawandshakeapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart(){
        super.onStart()
        var drawState = false
        findViewById<ImageView>(R.id.Classic_Image).imageAlpha = 100
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

        findViewById<ImageButton>(R.id.DrawButton).setOnClickListener {
            if(!drawState){
                println("this is trace mode")
                TestActivity().onCreate()
            }else{
                println("this is classic mode")
                TestActivity().onCreate()
            }
        }
    }
}