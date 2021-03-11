package com.example.pillbuddy

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import android.app.TimePickerDialog
import android.content.Intent
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

const val EXTRA_MESSAGE = "com.example.pillbuddy.MESSAGE"

var mil_hour = 0
var userID :String?  = null
open class CreateNotificationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notification_page)
        userID = intent.getStringExtra("UserID")
    }
    // function to move to the ConfirmNotification page while passing the data entered in the text boxes
    fun createNotifButton(view: View) {
        // gets the string that will be passed to the next activity page
        //Name of Medication
        val medName = findViewById<EditText>(R.id.editTextTextPersonName3)
        val message = medName.text.toString()
        //create new intent and pass the string
        val intent = Intent(this, ConfirmNotificationPage::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }

        // gets the string of the edit text for the dosage amount to pass to next activity
        //dosage amount
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

        var daysString : String? = null
        //if box is checked add day to days array
        if(mondayBox.isChecked){
            days[0] = "monday"
            daysString = "M"
        }
        if(tuesdayBox.isChecked){
            days[1] = "tuesday"
            if(daysString != null) {
                daysString += "T"
            }
            else{
                daysString = "T"
            }
        }
        if(wednesdayBox.isChecked){
            days[2] = "wednesday"
            if(daysString != null) {
                daysString += "W"
            }
            else{
                daysString = "W"
            }
        }
        if(thursdayBox.isChecked){
            days[3] = "thursday"
            if(daysString != null) {
                daysString += "R"
            }
            else{
                daysString = "R"
            }
        }
        if(fridayBox.isChecked){
            days[4] = "friday"
            if(daysString != null) {
                daysString += "F"
            }
            else{
                daysString = "F"
            }
        }
        if(saturdayBox.isChecked){
            days[5] = "saturday"
            if(daysString != null) {
                daysString += "S"
            }
            else{
                daysString = "S"
            }
        }
        if(sundayBox.isChecked){
            days[6] = "sunday"
            if(daysString != null) {
                daysString += "U"
            }
            else{
                daysString = "U"
            }
        }
        intent.putExtra("days", days)
        startActivity(intent)


        val medTime = mil_hour.toString() + minutes.toString()
        //API call here to send the data input
        //Post method
        Log.d("user","$userID, $daysString, $medTime")
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/post-notification"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", userID)
            postData.put("medication", medName.text.toString())
            postData.put("dosage", dosageMessage)
            postData.put("frequency", daysString)
            postData.put("time", medTime)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            //make a toast notification bubble when successful for now
            Response.Listener { response -> Toast.makeText(this, "Password change successful", Toast.LENGTH_SHORT).show()},
            Response.ErrorListener { error -> error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
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
        mil_hour = hourOfDay
        minutes = minute
    }
}