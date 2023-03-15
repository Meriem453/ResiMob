package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.residence_app.databinding.ActivitySportDetailsBinding

class SportDetails : AppCompatActivity() {
    lateinit var binding:ActivitySportDetailsBinding
lateinit var sat:CardView
    lateinit var sun:CardView
    lateinit var mon:CardView
    lateinit var tus:CardView
    lateinit var wed:CardView
    lateinit var thu:CardView
    lateinit var fri:CardView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_details)

        binding=ActivitySportDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
         sat=findViewById<CardView>(R.id.sat)
         sun=findViewById<CardView>(R.id.sun)
         mon=findViewById<CardView>(R.id.mon)
         tus=findViewById<CardView>(R.id.tus)
         wed=findViewById<CardView>(R.id.wed)
         thu=findViewById<CardView>(R.id.thu)
         fri=findViewById<CardView>(R.id.fri)


    }

    fun onCardClick(view: View) {
        sat.background=resources.getDrawable(R.drawable.sport_gray_background)
        sun.background=resources.getDrawable(R.drawable.sport_gray_background)
        mon.background=resources.getDrawable(R.drawable.sport_gray_background)
        tus.background=resources.getDrawable(R.drawable.sport_gray_background)
        wed.background=resources.getDrawable(R.drawable.sport_gray_background)
        thu.background=resources.getDrawable(R.drawable.sport_gray_background)
        fri.background=resources.getDrawable(R.drawable.sport_gray_background)




        view.background=resources.getDrawable(R.drawable.blue_background)
    }
}