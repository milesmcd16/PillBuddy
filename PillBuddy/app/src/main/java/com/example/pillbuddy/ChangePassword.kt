package com.example.pillbuddy

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    fun changePasswordClick(view: View)
    {
        //Data from user entry to put in body of API call
        // current password, new password, and current userid
        val currentPW = findViewById<EditText>(R.id.CurrentPassword)
        val newPW = findViewById<EditText>(R.id.editTextTextPassword3)
        val confirmPW = findViewById<EditText>(R.id.editTextTextPassword8)
        val userID = NotificationDataHelper.notificationList[0].getUserId()

        //Check if Passwords match
        //Return an error message if they don't match
        if(newPW.text.toString() != confirmPW.text.toString()) {
            val msg = "Passwords do not match"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            return
        }

        //POST method
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/changepw"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", userID)
            postData.put("currentPassword", currentPW)
            postData.put("newPassword", newPW)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            //make a toast notification bubble when successful for now
            Response.Listener { response -> Toast.makeText(this, "Password change successful", Toast.LENGTH_SHORT).show()},

            //make a toast notification indicating error
            Response.ErrorListener { error ->
                Toast.makeText(this, "Password unsuccessful try again alter", Toast.LENGTH_SHORT).show()
                error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
    }

}