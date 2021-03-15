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

class RemoveBuddy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_buddy_page)
    }

    fun ToHome(view: View){
        val intent = Intent(this, CaregiverHomePage::class.java)
        startActivity(intent)
    }

    fun RemoveButtonPatient(view: View) {
        //save variables required for removing buddy from database, (username of target buddy)
        val buddyName = findViewById<EditText>(R.id.editTextTextPersonName10).text.toString()
        val userID = NotificationDataHelper.notificationList[0].getUserId()
        val queue = Volley.newRequestQueue( this)
        val url = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/rembuddy?currUser=" + userID + "&remBuddy" + buddyName

        val jsonObjectRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String>() { response ->
                    //makes a message to notify that the buddy has been removed
                    Toast.makeText(this, "Successfully removed buddy", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Toast.makeText(this, "No buddy has been removed check name and try again", Toast.LENGTH_SHORT).show()
                })
        queue.add(jsonObjectRequest)

    }
}