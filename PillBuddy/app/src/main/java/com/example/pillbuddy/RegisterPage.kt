package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
    }
    //function that creates an intent to move to the home page when the button is pressed
    fun registerToHome(view: View) {

        //API Call Variables
        val userID = findViewById<EditText>(R.id.editTextTextPersonName5).text.toString()
        val password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()
        val confirmpassword = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()
        val email = findViewById<EditText>(R.id.editTextTextPersonName11).text.toString()
        val firstName = findViewById<EditText>(R.id.editTextTextPersonName4).text.toString()
        val lastName = findViewById<EditText>(R.id.editTextTextPersonName8).text.toString()
        //userType = a boolean value for the sake of sending through body of API call
        val userType = findViewById<CheckBox>(R.id.checkBox5).isChecked
        val queue = Volley.newRequestQueue( this)

        //check if password boxes match
        //return if no match and notify user with failure to match
        if(password != confirmpassword)
        {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        var type = "patient"
        if(userType){
            type = "caregiver"
        }
        //POST METHOD API call
        //When a proper response is received from the call create an intent to move to the mainpage
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/register"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", userID)
            postData.put("password", password)
            postData.put("usertype", type)
            postData.put("email", email)
            postData.put("firstname", firstName)
            postData.put("lastname", lastName)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            //On success bring user to the sign in page
            //Maybe make a toast message to say account created?
            Response.Listener { response ->
                Toast.makeText(this, "Account created please log in", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInPage::class.java)
                startActivity(intent)},
            Response.ErrorListener { error -> error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
    }
}