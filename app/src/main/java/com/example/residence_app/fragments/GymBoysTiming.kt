package com.example.residence_app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.GymTimingAdapter
import com.example.residence_app.adapters.TimingAdapter


class GymBoysTiming : Fragment() {
    var isAdmin=false
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_gym_boys_timing, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.gym_boystiming_rec)
        initRecyclerView(recycler)
        return view
    }

    private fun initRecyclerView(recycler: RecyclerView) {
        val adapter= GymTimingAdapter(requireContext(),requireActivity(),isAdmin)
        adapter.getGymBoysdata()
        recycler.adapter=adapter
        recycler.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }
}