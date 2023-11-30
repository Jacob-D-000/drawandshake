package com.drawandshake.drawandshakeapp

import androidx.appcompat.app.AppCompatActivity

class SaveDrawing(private val activity: AppCompatActivity) : DrawCanvas(activity){
    fun save(){

        println("array size: " + getxyPointsSize())
    }
}