package com.example.pillbuddy

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    fun changePasswordClick(view: View)
    {
        val currentPW = findViewById<EditText>(R.id.CurrentPassword)
        val newPW = findViewById<EditText>(R.id.editTextTextPassword3)
        val confirmPW = findViewById<EditText>(R.id.editTextTextPassword8)


        //Check if Passwords match
        if(newPW != confirmPW)
        {
            val msg = "Passwords do not match"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            return
        }
        /*
        //API call
        val userID = NotificationDataHelper.notificationList[0].getUserId()
        val queue = Volley.newRequestQueue( this)

        //POST method
        val url = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio"
        val jsonObjectRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String>() { response ->
                var jsonResponseCalendar = response.toString()
            },
            Response.ErrorListener {//something here maybe //
            })
        queue.add(jsonObjectRequest)

         */
    }

}