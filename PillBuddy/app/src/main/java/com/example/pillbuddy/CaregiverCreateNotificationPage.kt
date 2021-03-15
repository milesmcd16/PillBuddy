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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject



open class CaregiverCreateNotificationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregiver_create_notification_page)
    }
    // function to move to the ConfirmNotification page while passing the data entered in the text boxes
    fun createNotifButton(view: View) {
        // gets the string that will be passed to the next activity page
        //Name of Medication
        val medName = findViewById<EditText>(R.id.editTextTextPersonName3)
        val message = medName.text.toString()
        //create new intent and pass the string
        val intent = Intent(this, CaregiverConfirmNotificationPage::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }

        // gets the string of the edit text for the dosage amount to pass to next activity
        //dosage amount
        val dosageText = findViewById<EditText>(R.id.editTextTextPersonName9)
        val dosageMessage = dosageText.text.toString()
        // give the dosage message to the intent
        intent.putExtra("Dosage", dosageMessage)

        val patientText = findViewById<EditText>(R.id.editTextTextPersonName12)
        val patientMessage = patientText.toString()
        intent.putExtra("Patient_name", patientMessage)


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
        //Post method
        val userID = NotificationDataHelper.notificationList[0].getUserId()
        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/post-notification"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", userID)
            postData.put("nameMed", medName)
            postData.put("amtMed", dosageMessage)
            postData.put("timeNotif", days)
            postData.put("patientName", patientMessage)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            //make a toast notification bubble when successful for now
            Response.Listener { response -> Toast.makeText(this, "Notification created for " + patientMessage + "successfully", Toast.LENGTH_SHORT).show()},
            Response.ErrorListener { error -> error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
    }
    // function to show the time picker box when the button is pressed
    fun showTimePickerDialog(v: View) {
        //show timepicker
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }
    fun homeButton(view: View) {
        val intent = Intent(this, CaregiverHomePage::class.java)
        startActivity(intent)
    }
}
