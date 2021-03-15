package com.example.pillbuddy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import org.json.JSONException
import org.json.JSONObject

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)
    }
    //function to create an intent to move to the home page activity when the button is pressed
    fun signInToHome(view: View) {

        //login data
        val userID = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()


        //POST METHOD
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/login"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", userID)
            postData.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            //make a toast notification bubble when successful login happens
            //Move user to the homepage on successful login
            Response.Listener { response ->
                checkHTTP(response.toString(), userID)
            },
                Response.ErrorListener() {error -> error.printStackTrace()})
        requestQueue.add(jsonObjectRequest)
    }
    //function to create an intent to move to the Register Page activity when the button is pressed
    fun signInToRegister(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }
    fun checkHTTP(yes: String, userID: String)
    {
        var codestring = JSONObject(yes)
        var code = codestring.getInt("statusCode")
        //code 400 is for login failure wrong credentials
        if(code == 400)
        {
            Toast.makeText(this, "Login failed: Wrong credentials", Toast.LENGTH_SHORT).show()
            return
        }
        //code 200 is for patient login success
        if (code == 200)
        {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomePage::class.java).apply {
                putExtra("userID", userID)
            }
            startActivity(intent)
            return
        }
        //code 201 is for caregiver login success
        if(code == 201)
        {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CaregiverHomePage::class.java).apply {
                    putExtra("userID", userID)
                }
                startActivity(intent)
            return
        }

    }
}