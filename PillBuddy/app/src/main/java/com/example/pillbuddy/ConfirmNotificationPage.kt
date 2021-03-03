package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView


class ConfirmNotificationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_notification_page)


        //get the string passed in the intent for the name of the medication
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        //get the text box for where the name of the medication should be displayed
        val textView = findViewById<TextView>(R.id.textView).apply {
            //set the text of the new text box to the value from the intent
            text = message
        }
        //get the string passed in the intent for the type of the medication
        val typeText = intent.getStringExtra("MedicationType")
        //get the text box for where the type of the medication should be displayed
        val typeView = findViewById<TextView>(R.id.textView2).apply {
            //set the text of the new text box to the value from the intent
            text = typeText
        }
        //get the string passed in the intent for the dosage of the medication
        val dosageText = intent.getStringExtra("Dosage")
        //get the text box for where the dosage of the medication should be displayed
        val dosageView = findViewById<TextView>(R.id.textView3).apply {
            //set the text of the new text box to the value from the intent
            text = dosageText
        }

        // Create a notification from the information entered by the user.
        // For demonstration purposes, this is delivered immediately.
        NotificationHandler.createNotification(this, "It's time to take your $message",
               "$typeText", "$dosageText", false)
    }
    //function that creates the intent to move to the create notification page and moves to that
    // page when the button is pressed
    fun createAnotherNotifButton(view: View){
        val intent = Intent(this, CreateNotificationPage::class.java)
        startActivity(intent)
    }
    //function that creates the intent to return to the home page and moves to that page when
    // the button is pressed
    fun mainMenuButton(view: View){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}