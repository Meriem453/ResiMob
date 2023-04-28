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


class BathroomBoysTiming : Fragment() {
var isAdmin=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_bathroom_boys_timing, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.bathroom_boystiming_rec)
        initRecyclerView(recycler)

        return view
    }

    private fun initRecyclerView(recycler: RecyclerView) {
        val adapter= TimingAdapter(requireContext(),requireActivity(),isAdmin)
        adapter.getBathroomBoysTimingData()
        recycler.adapter=adapter
        recycler.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }


}