package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.residence_app.adapters.SpinnerAdapter
import com.example.residence_app.databinding.ActivityProblemBinding

class Problem : AppCompatActivity() {
    lateinit var binding:ActivityProblemBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_problem)
        supportActionBar?.hide()

val arr= ArrayList<String>()
        arr.add("pr1")
        arr.add("pr2")
        arr.add("pr3")
        arr.add("pr4")
val adapter= SpinnerAdapter(arr,baseContext)
binding.autoCompletePresident.setAdapter(adapter)
        binding.autoCompletePresident.setOnClickListener {
            Toast.makeText(baseContext,"i am here",Toast.LENGTH_SHORT).show()
        }



    }
}