package com.example.m07_sensors

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.Formatter

/**
 * Created by Russ on 08/04/2014.
 */
class BouncingBallView(context: Context?) : View(context),
    SensorEventListener {
    private val balls = ArrayList<Ball>() // list of Balls
    private var ball_1 // use this to reference first ball in arraylist
            : Ball

    // Creating Square and Square Array
    private val squares = ArrayList<Square>() //list of Squares
    private val square_1: Square
    private val box: Box

    // Status message to show Ball's (x,y) position and speed.
    private val statusMsg = StringBuilder()
    private val formatter = Formatter(statusMsg)
    private val paint: Paint
    private var string_line = 1 //
    private var string_x = 10
    private val string_line_size = 40 // pixels to move down one line
    private val debug_dump1: ArrayList<String?> = ArrayList<String?>()
    private val debug_dump2 = arrayOfNulls<String>(200)

    // For touch inputs - previous touch (x, y)
    private var previousX = 0f
    private var previousY = 0f
    var ax = 0.0 // Store here for logging to screen
    var ay = 0.0 //
    var az = 0.0 //

    // Constructor
    init {

        // Init the array
        for (i in 1..199) {
            debug_dump2[i] = "  "
        }

        // create the box
        box = Box(Color.DKGRAY) // ARGB

        //ball_1 = new Ball(Color.GREEN);
        balls.add(Ball(Color.GREEN))
        ball_1 = balls[0] // points ball_1 to the first; (zero-ith) element of list
        Log.w("BouncingBallLog", "Just added a bouncing ball")

        //ball_2 = new Ball(Color.CYAN);
        balls.add(Ball(Color.CYAN))
        Log.w("BouncingBallLog", "Just added another bouncing ball")

        //Creating Square for testing
        squares.add(Square())
        square_1 = squares[0]

        // Set up status message on paint object
        paint = Paint()

        // Set the font face and size of drawing text
        paint.typeface = Typeface.MONOSPACE
        paint.textSize = 32f
        paint.color = Color.CYAN

        // To enable keypad
        this.isFocusable = true
        this.requestFocus()
        // To enable touch mode
        this.isFocusableInTouchMode = true
    }

    // Called back to draw the view. Also called after invalidate().
    override fun onDraw(canvas: Canvas) {
        // Draw the components
        box.draw(canvas)
        //canvas.drawARGB(0,25,25,25);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        //Drawing each ball, then moving them with collision
        for (b in balls) {
            b.draw(canvas) //draw each ball in the list
            b.moveWithCollisionDetection(box) // Update the position of the ball
        }

        //Drawing each square, then moving them with collision
        for (s in squares) {
            s.draw(canvas) //draw each ball in the list
            s.moveWithCollisionDetection(box) // Update the position of the ball
        }

        // Draw the status message to the screen
//        statusMsg.delete(0, statusMsg.length());   // Empty buffer
//        formatter.format("Ball@(%3.0f,%3.0f),Speed=(%2.0f,%2.0f)", ball_1.x, ball_1.y,
//                ball_1.speedX, ball_1.speedY);
//        canvas.drawText(statusMsg.toString(), 10, 30, paint);


        // inc-rotate string_line
        if (string_line * string_line_size > box.yMax) {
            string_line = 1 // first line is status
            debug_dump1.clear()
        } else {
            string_line++
        }

        // inc-rotate string_x
        if (string_x > box.xMax) {
            string_x = 10 // first line is status
        } else {
            string_x++
        }

        // Array of String (uses more mem, but changes less)
        debug_dump2[string_line] = "Acc($ax, $ay ,$az)"
        for (i in 1 until debug_dump2.size) {
            // un-comment to print debug code on screen
            //canvas.drawText(debug_dump2[i], string_x, i * string_line_size, paint);
        }

        // ArrayList (more new's, allocation of RAM)
//        String newString = new String("AL-Ball(" + string_line + "  " + ball_1.x + " ," + ball_1.y + ") ");
//        debug_dump1.add(newString);
//        for (int i = 1; i < debug_dump1.size(); i++) {
//            canvas.drawText(debug_dump1.get(i), 700, i * string_line_size, paint);
//        }


        // Delay on UI thread causes big problems!
        // Simulates doing busy work or waits on UI (DB connections, Network I/O, ....)
        //  I/Choreographer? Skipped 64 frames!  The application may be doing too much work on its main thread.
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }

        // Check what happens if you draw the box last
        //   box.draw(canvas);

        // Check what happens if you don't call invalidate (redraw only when stopped-started)
        // Force a re-draw
        this.invalidate()
    }

    // Called back when the view is first created or its size changes.
    public override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        // Set the movement bounds for the ball
        box[0, 0, w] = h
        Log.w("BouncingBallLog", "onSizeChanged w=$w h=$h")
    }

    // Touch-input handler
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentX = event.x
        val currentY = event.y
        val deltaX: Float
        val deltaY: Float
        val scalingFactor = 5.0f / if (box.xMax > box.yMax) box.yMax else box.xMax
        val slow_down_speed_factor = 10.0f
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                // Modify rotational angles according to movement
                deltaX = (currentX - previousX) / slow_down_speed_factor
                deltaY = (currentY - previousY) / slow_down_speed_factor
                ball_1.speedX += (deltaX * scalingFactor).toDouble()
                ball_1.speedY += (deltaY * scalingFactor).toDouble()
                //Log.w("BouncingBallLog", " Xspeed=" + ball_1.speedX + " Yspeed=" + ball_1.speedY);
                Log.w(
                    "BouncingBallLog",
                    "x,y= $previousX ,$previousY  Xdiff=$deltaX Ydiff=$deltaY"
                )
                balls.add(
                    Ball(
                        Color.RED,
                        previousX,
                        previousY,
                        deltaX,
                        deltaY
                    )
                ) // add ball at every touch event

                // A way to clear list when too many balls
                if (balls.size > 20) {
                    // leave first ball, remove the rest
                    Log.v("BouncingBallLog", "too many balls, clear back to 1")
                    balls.clear()
                    balls.add(Ball(Color.GREEN))
                    ball_1 = balls[0] // points ball_1 to the first (zero-ith) element of list
                }
            }
        }
        // Save current x, y
        previousX = currentX
        previousY = currentY

        // Try this (remove other invalidate(); )
        //this.invalidate();
        return true // Event handled
    }

    override fun onSensorChanged(event: SensorEvent) {

        //Log.v("onSensorChanged", "event=" + event.toString());

        // Lots of sensor types...get which one, unpack accordingly
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            ax = -event.values[0].toDouble() // turns out x is backwards...on my screen?
            ay = event.values[1].toDouble() // y component of Accelerometer
            az = event.values[2].toDouble() // z component of Accelerometer
            for (b in balls) {
                b.setAcc(ax, ay, az) //draw each ball in the list
            }
            for (s in squares) {
                s.setAcc(ax, ay, az) //draw each square in the list
                //setRandomColor() Gets called during collision checks inside of itself.
            }

            //square_1.setAcc(ax,ay,az);

            //Log.v("onSensorChanged", "ax=" + ax + " ay=" + ay + " az=" + az);
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        Log.v("onAccuracyChanged", "event=$sensor")
    }
}