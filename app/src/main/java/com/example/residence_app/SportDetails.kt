package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.SportDaysAdapter
import com.example.residence_app.databinding.ActivitySportDetailsBinding

class SportDetails : AppCompatActivity() {
    lateinit var binding:ActivitySportDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_details)

        supportActionBar?.hide()

        binding=ActivitySportDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array=resources.getStringArray(R.array.days)
        binding.sportRec.adapter=SportDaysAdapter(baseContext,array,fragmentManager)
        binding.sportRec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.HORIZONTAL,false)


    }



}