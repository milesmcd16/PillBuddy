package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AccountSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)
    }
    // function to move to the homepage activity when the button is pressed
    fun settingsToHome(view: View){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
    // function to move to the homepage activity when the button is pressed
    fun addBuddyButton(view: View){
        val intent = Intent(this, AddBuddyPage::class.java)
        startActivity(intent)
    }
}