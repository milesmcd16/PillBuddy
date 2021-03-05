package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
    }
    //function that creates an intent to move to the home page when the button is pressed
    fun registerToHome(view: View) {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)

        //maybe change to sign in page?
    }
}