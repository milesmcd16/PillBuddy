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
class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
    }
    //function that creates an intent to move to the home page when the button is pressed
    fun registerToHome(view: View) {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        //API Call Variables
        val userID = findViewById<EditText>(R.id.editTextTextPersonName5)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val email = findViewById<EditText>(R.id.editTextTextPersonName4)
        val queue = Volley.newRequestQueue( this)


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
        //maybe change to sign in page?
    }
}