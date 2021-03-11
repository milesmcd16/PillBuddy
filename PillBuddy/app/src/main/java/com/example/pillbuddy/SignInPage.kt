package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
        val userID = findViewById<EditText>(R.id.editTextTextPersonName)
        val password = findViewById<EditText>(R.id.editTextTextPersonName2)


        //POST METHOD
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/changepw"
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
                Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            },

            //On failure notify wrong credentials
            Response.ErrorListener { error ->
                Toast.makeText(this, "Login failed: Wrong credentials", Toast.LENGTH_SHORT).show()
                error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
    }
    //function to create an intent to move to the Register Page activity when the button is pressed
    fun signInToRegister(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }
}