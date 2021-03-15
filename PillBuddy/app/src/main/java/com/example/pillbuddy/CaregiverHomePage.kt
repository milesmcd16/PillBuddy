package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CaregiverHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregiver_home_page)
    }
    //function to create the intent to move to the create notification page activity and then move
    //when the button is pressed
    fun createNotifButton(view: View) {
        val intent = Intent(this, CaregiverCreateNotificationPage::class.java)
        startActivity(intent)
    }
    //function to create the intent to move to the calendar page activity and then move
    //when the button is pressed
    fun viewCalendarButton(view: View){
        val intent = Intent(this, CalendarPage::class.java)
        startActivity(intent)
    }
    //function to create the intent to move to the medication list page activity and then move
    //when the button is pressed
    fun viewMedicationListButton(view: View){
        val intent = Intent(this, MedicationListPage::class.java)
        startActivity(intent)
    }
    //function to create the intent to move to the account settings page activity and then move
    //when the button is pressed
    fun accountSettingsButton(view: View){
        val intent = Intent(this, AccountSettings::class.java)
        startActivity(intent)
    }
}