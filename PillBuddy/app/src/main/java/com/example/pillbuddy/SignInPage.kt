package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)
    }
    //function to create an intent to move to the home page activity when the button is pressed
    fun signInToHome(view: View) {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        //api call with login data?
        val userID = findViewById<EditText>(R.id.editTextTextPersonName)
        val password = findViewById<EditText>(R.id.editTextTextPersonName2)
        val queue = Volley.newRequestQueue( this)

        /*
        //POST METHOD SADGE
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
    //function to create an intent to move to the Register Page activity when the button is pressed
    fun signInToRegister(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
        
    }
}