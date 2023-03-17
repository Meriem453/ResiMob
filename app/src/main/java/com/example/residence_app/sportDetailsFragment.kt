package com.example.residence_app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.SportSportsAdapter


class sportDetailsFragment : android.app.Fragment() {

lateinit var c:Context

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view=inflater.inflate(R.layout.fragment_sport_details, container, false)
            val rec=view.findViewById<RecyclerView>(R.id.sports_rec)
            val adapter=SportSportsAdapter(c)
            adapter.getSatData()
            rec.adapter=adapter

            rec.layoutManager=LinearLayoutManager(c,RecyclerView.VERTICAL,false)

            return view


        }


    }