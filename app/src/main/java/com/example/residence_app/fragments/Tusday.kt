package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.SportSportsAdapter


class Tusday : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_tusday, container, false)
        val rec= view.findViewById<RecyclerView>(R.id.tusday_rec)
        val adapter= SportSportsAdapter(requireContext())
        adapter.getData(2)
        rec.adapter=adapter
        rec.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        return view
    }


}