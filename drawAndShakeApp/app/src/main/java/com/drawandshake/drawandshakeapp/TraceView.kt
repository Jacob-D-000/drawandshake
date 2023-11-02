package com.drawandshake.drawandshakeapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TraceView: View {
    constructor(context: Int) : super(context)
    constructor(context : Context, attrs : AttributeSet) : super(context, attrs)
    val screen =  Canvas()
    val pen = Paint()

}