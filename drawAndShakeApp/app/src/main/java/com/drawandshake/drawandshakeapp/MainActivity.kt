package com.drawandshake.drawandshakeapp

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Switch>(R.id.menu_switch).setOnCheckedChangeListener { _, isCkecked ->
            val message = if (isCkecked) "Switch1:ON" else "Switch1:OFF"
            println(message)
        }
    }
}