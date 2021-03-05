package com.example.pillbuddy

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_page)
    }

    fun button(view: View) {
        val textyBox = findViewById<EditText>(R.id.editTextTextPersonName6)
        val texty = textyBox.text.toString()

        //text is what was entered into the box
        //do api stuff here


        val resultsBox = findViewById<TextView>(R.id.textView21).apply{
            //put data from api call into other text box, for now it just copies whats in the parameter box
            text = texty
        }

    }
}