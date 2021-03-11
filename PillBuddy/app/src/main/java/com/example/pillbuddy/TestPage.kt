package com.example.pillbuddy

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class TestPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_page)
    }

    fun button(view: View) {
        val textyBox = findViewById<EditText>(R.id.editTextTextPersonName6)
        val texty = textyBox.text.toString()
        val texty2 = findViewById<EditText>(R.id.editTextTextPersonName7).text.toString()
        val textView = findViewById<TextView>(R.id.textView21)


        val postUrl = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/login"
        val requestQueue = Volley.newRequestQueue(this)

        val postData = JSONObject()
        try {
            postData.put("userid", texty)
            postData.put("password", texty2)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            Response.Listener { response -> textView.text = response.toString() },
            Response.ErrorListener { error -> error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)


        //text is what was entered into the box
        //do api stuff here
        //val queue = Volley.newRequestQueue(this)
        //val url = "https://4cxr4yahc7.execute-api.us-east-2.amazonaws.com/TestEnvrio/test-call?userId=" + texty
        //val jsonObjectRequest = StringRequest(Request.Method.GET, url,
        //    Response.Listener<String>() { response ->
        //        textView.text = "Response: %s".format(response.toString())
        //    },
        //    Response.ErrorListener { textView.text = "NOPE" })
        //queue.add(jsonObjectRequest)

    }
}