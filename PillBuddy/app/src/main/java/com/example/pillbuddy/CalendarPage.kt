package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pillbuddy.NotificationDataHelper

class CalendarPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_page)

        //get the idea of the calendar
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        //when a new date on the calendar is selected, print message for if a notification is set for that day
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val msg = "No Notifications set for " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarPage, msg, Toast.LENGTH_SHORT).show()

            //API call here to pull the notification data for that date?
            // we could also make it so that the local notification data is pulled instead of API call
            val userID = NotificationDataHelper.notificationList[0].getUserId()
            val queue = Volley.newRequestQueue( this)


            //Need to figure out how to fill calendar with the response
            //Response is going to come in json format
            //populate by searching with alert ids
            //maybe refill the local user database
            val url = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio?currUser=" + userID
            val jsonObjectRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String>() { response ->
                    var jsonResponseCalendar = response.toString()
                },
                Response.ErrorListener {//something here maybe //
                     })
            queue.add(jsonObjectRequest)
        }
    }

    // function that moves the application back to the Home page when the button is pressed
    fun calendarToHomeButton(view: View){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}