package com.example.pillbuddy

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import android.app.TimePickerDialog
import android.content.Intent
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.pillbuddy.NotificationData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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

        // gets the string of the edit text for the dosage amount to pass to next activity
        val dosageText = findViewById<EditText>(R.id.editTextTextPersonName9)
        val dosageMessage = dosageText.text.toString()
        // give the dosage message to the intent
        intent.putExtra("Dosage", dosageMessage)

        //get time and convert to 12 hours
        var pm :Boolean? = false
        if (hour > 12) {
            pm = true
            hour -= 12
        }
        intent.putExtra("Hour", hour)
        intent.putExtra("Minute", minutes)
        intent.putExtra("pm", pm)

        //create days array
        val days = arrayOfNulls<String>(7)
        //get the checkboxes from ui
        val mondayBox = findViewById<CheckBox>(R.id.MondayCheckBox)
        val tuesdayBox = findViewById<CheckBox>(R.id.TuesdayCheckBox)
        val wednesdayBox = findViewById<CheckBox>(R.id.WednesdayCheckBox)
        val thursdayBox = findViewById<CheckBox>(R.id.ThursdayCheckBox)
        val fridayBox = findViewById<CheckBox>(R.id.FridayCheckBox)
        val saturdayBox = findViewById<CheckBox>(R.id.SaturdayCheckBox)
        val sundayBox = findViewById<CheckBox>(R.id.SundayCheckBox)

        //if box is checked add day to days array
        if(mondayBox.isChecked){
            days[0] = "monday"
        }
        if(tuesdayBox.isChecked){
            days[1] = "tuesday"
        }
        if(wednesdayBox.isChecked){
            days[2] = "wednesday"
        }
        if(thursdayBox.isChecked){
            days[3] = "thursday"
        }
        if(fridayBox.isChecked){
            days[4] = "friday"
        }
        if(saturdayBox.isChecked){
            days[5] = "saturday"
        }
        if(sundayBox.isChecked){
            days[6] = "sunday"
        }
        intent.putExtra("days", days)
        startActivity(intent)

        //API call here to send the data input
        //
        //
        //API call here to send the data input
        //POst method have to figure some stuff out
        val userID = NotificationDataHelper.notificationList[0].getUserId()
        val queue = Volley.newRequestQueue( this)

        val url = ("https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio?currUser=")
        val jsonObjectRequest = StringRequest(
                Request.Method.POST, url,
                Response.Listener<String>() { response ->
                    var jsonResponseCalendar = response.toString()
                },
                Response.ErrorListener {//something here maybe //
                })
        queue.add(jsonObjectRequest)
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
        hour = hourOfDay
        minutes = minute
    }
}