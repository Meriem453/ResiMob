package com.example.residence_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get

class FeedbackActivity : AppCompatActivity() {
    lateinit var option : Spinner
    lateinit var president : String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        supportActionBar?.hide()
        option = findViewById(R.id.spinner_id)
        val options = arrayOf("problem", "feedback", "Gym", "restaurant")


        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               president = options[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    }

