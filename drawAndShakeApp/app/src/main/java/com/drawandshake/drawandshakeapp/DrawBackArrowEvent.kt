package com.drawandshake.drawandshakeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

/**
 * Liam notes
 * now this isn't to bad SIKE
 * why TF are we taking a PRIVATE val and putting it in A CLASS I never used that in any other language and
 * I wont be surprised id its not in java but maybe it is - I digress
 *
 * here we are taking a xml element (back arrow) from are classic/trace activity (that is why keyword 'this' is
 * in the installation of the classes)
 * I then apply a event listener to the button
 * when the button is pressed it loads the menu activity
 */
class DrawBackArrowEvent(private val activity: AppCompatActivity){
    //negates on click events for back arrow button
    @SuppressLint("ClickableViewAccessibility")
    fun backPressed() {
        //On touch listener for Back arrow button
        activity.findViewById<ImageButton>(R.id.BackArrow).setOnTouchListener { _, motionEvent ->
            //Will happen when image is pressed other wise event is set to false
            when (motionEvent.action){
                //change opacity of image when user holds finger on image
                MotionEvent.ACTION_DOWN -> {
                    activity.findViewById<ImageButton>(R.id.BackArrow).alpha = 0.5f
                }
                //when user lets go or the hold action is stoped it will loud the proper page
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                }
            }
            false
        }
    }
}