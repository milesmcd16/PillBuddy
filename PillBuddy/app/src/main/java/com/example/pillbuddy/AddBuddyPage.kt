package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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

        val userID = NotificationDataHelper.notificationList[0].getUserId()
        val queue = Volley.newRequestQueue( this)
        val newBuddy = findViewById<EditText>(R.id.editTextTextPersonName90).text.toString()
        val url = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio?currUser=" + userID + "&newBuddy=" + newBuddy
        val jsonObjectRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String>() { response ->
                var jsonResponseCalendar = response.toString()
                val msgSuccess = "Buddy Invited!"
                Toast.makeText(this@AddBuddyPage, msgSuccess, Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {val msgFail = "Please try again later"
                Toast.makeText(this@AddBuddyPage, msgFail, Toast.LENGTH_SHORT).show()
            })
        queue.add(jsonObjectRequest)

    }
}