package com.example.residence_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.SportSportsAdapter


class sportDetailsFragment : android.app.Fragment() {

lateinit var c:Context
 var dayPosition:Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view=inflater.inflate(R.layout.fragment_sport_details, container, false)
            val rec=view.findViewById<RecyclerView>(R.id.sports_rec)
            val adapter=SportSportsAdapter(c,dayPosition)
            adapter.getData()
            rec.adapter=adapter

            rec.layoutManager=LinearLayoutManager(c,RecyclerView.VERTICAL,false)

            return view


        }


    }