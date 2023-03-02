package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.residence_app.databinding.ActivitySportDetailsBinding

class SportDetails : AppCompatActivity() {
    lateinit var binding:ActivitySportDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_details)

        binding=ActivitySportDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.foot.setOnClickListener {
            startActivity(Intent(baseContext,Football::class.java))
        }
    }
}