package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationManagerCompat;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationHandler.createNotificationChannel(this,
                NotificationManagerCompat.IMPORTANCE_MAX, false,
                "Pill Buddy", "Notification channel for Pill Buddy app")

        setContentView(R.layout.activity_main)
    }
    // function to load sign in page when the sign in button is pressed
    fun signIn(view: View) {
       val intent = Intent(this, SignInPage::class.java)
        startActivity(intent)
    }
    // function to load register page when the register button is pressed
    fun register(view: View) {
        val intent = Intent(this, RegisterPage::class.java)
        startActivity(intent)
    }

    fun test(view: View){
        val intent = Intent(this, TestPage::class.java)
        startActivity(intent)
    }
}