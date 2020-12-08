package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)
    }
    //function to create an intent to move to the home page activity when the button is pressed
    fun signInToHome(view: View) {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
    //function to create an intent to move to the Register Page activity when the button is pressed
    fun signInToRegister(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }
}