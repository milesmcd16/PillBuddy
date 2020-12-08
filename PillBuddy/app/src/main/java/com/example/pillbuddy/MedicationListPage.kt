package com.example.pillbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MedicationListPage : AppCompatActivity() {

    //create simple array of medication for front end prototype
    var medicationArray = arrayOf("Advil","Pizza","Dog", "Drink", "Bandaid", "Cats")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication_list_page)

        //create an adapter to adapt the array to the listview
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,medicationArray)
        val listView = findViewById<ListView>(R.id.listview_1)
        listView.adapter = adapter

    }
    //function to create an intent to move to the home page when the button is pressed and then move
    fun medListtoHome(view: View){
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}