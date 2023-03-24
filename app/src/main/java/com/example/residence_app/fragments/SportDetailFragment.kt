package com.example.residence_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.SportSportsAdapter


class SportDetailsFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view=inflater.inflate(R.layout.fragment_sport_details, container, false)
            val rec=view.findViewById<RecyclerView>(R.id.sports_rec)
            val adapter=SportSportsAdapter(requireContext())
            adapter.getData(dayPosition)
            rec.adapter=adapter

            rec.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

            return view


        }
companion object Instance{
    var dayPosition:Int=0
}

    }