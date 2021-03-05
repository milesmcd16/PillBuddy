package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class AddBuddyPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_buddy_page)
    }
    // function to return to home page when the button is pressed
    fun addBuddyToHome(view: View){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
    //function to show the buddy has been invited screen
    fun buddyInvitedButton(view: View){
        //display a message saying buddy was invited successfully
        //maybe change this to display only if a true value is received
        //will have to double check
        val msg = "Buddy Invited!"
        Toast.makeText(this@AddBuddyPage, msg, Toast.LENGTH_SHORT).show()
    }
}