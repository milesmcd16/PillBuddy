package com.example.pillbuddy

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import android.app.TimePickerDialog
import android.content.Intent
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment


const val EXTRA_MESSAGE = "com.example.pillbuddy.MESSAGE"

open class CreateNotificationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notification_page)
    }
    // function to move to the ConfirmNotification page while passing the data entered in the text boxes
    fun createNotifButton(view: View) {
        // gets the string that will be passed to the next activity page
        val editText = findViewById<EditText>(R.id.editTextTextPersonName3)
        val message = editText.text.toString()
        //create new intent and pass the string
        val intent = Intent(this, ConfirmNotificationPage::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        // gets the string of the edit text box so that it can be passed to next activity
        val typeText = findViewById<EditText>(R.id.editTextTextPersonName8)
        val typeMessage = typeText.text.toString()
        // put the string into the intent
        intent.putExtra("MedicationType",typeMessage)

        // gets the string of the edit text for the dosage amount to pass to next activity
        val dosageText = findViewById<EditText>(R.id.editTextTextPersonName9)
        val dosageMessage = dosageText.text.toString()
        // give the dosage message to the intent
        intent.putExtra("Dosage", dosageMessage)

        startActivity(intent)
    }
    // function to show the time picker box when the button is pressed
    fun showTimePickerDialog(v: View) {
        //show timepicker
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }
}
//class for the time picker dialog box created in the create notification activity
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity,this, hour, minute, is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user

    }
}