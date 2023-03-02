package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.TimingAdapter


class FootballNational : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_football_national, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.football_national_rec)
        initRecyclerView(recycler)

        return view
    }

    private fun initRecyclerView(recycler: RecyclerView) {
          val adapter=TimingAdapter(requireContext())
        adapter.getFootballNationaltimingData()
        recycler.adapter=adapter
        recycler.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
    }


}