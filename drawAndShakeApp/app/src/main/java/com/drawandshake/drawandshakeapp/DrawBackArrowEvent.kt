package com.drawandshake.drawandshakeapp

import android.content.Intent
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
    fun backPressed() {
        activity.findViewById<ImageButton>(R.id.BackArrow).setOnClickListener(){
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}