package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pillbuddy.NotificationDataHelper

var hour = 0
var minutes = 0
var notifID = 0

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

        //get the string passed in the intent for the dosage of the medication
        val dosageText = intent.getStringExtra("Dosage")
        //get the text box for where the dosage of the medication should be displayed
        val dosageView = findViewById<TextView>(R.id.textView3).apply {
            //set the text of the new text box to the value from the intent
            text = dosageText
        }

        val hourText = intent.getIntExtra("Hour", 0)
        val minuteText = intent.getIntExtra("Minute", 0)
        val pm = intent.getBooleanExtra("pm", false)
        var timeText :String? = null

        timeText = if (pm){
            hourText.toString() + ":"   + minuteText.toString() + " PM"
        } else {
            hourText.toString() + ":" + minuteText.toString() + " AM"
        }

        val timeView = findViewById<TextView>(R.id.textView5).apply{
            text  = timeText
        }

        val daysArray = intent.getStringArrayExtra("days")
        var daysText :String? = null
        if (daysArray != null) {
            daysText = daysArray[1]
        }
        val typeView = findViewById<TextView>(R.id.textView2).apply {
            //set the text of the new text box to the value from the intent
            text = daysText
        }
        //create notification data object
        if(pm){
            hour += 12
        }
        val notification = NotificationData(notifID,null, message, dosageText, hour, minutes, daysArray)
        NotificationDataHelper.notificationList.add(0,notification)


        notifID += 1
        // Create a notification from the information entered by the user.
        // For demonstration purposes, this is delivered immediately.
        //NotificationHandler.createNotification(this, "It's time to take your $message",
               //"$typeText", "$dosageText", false)
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