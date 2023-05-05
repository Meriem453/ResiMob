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


class Saturday : Fragment() {

var isAdmin = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saturday, container, false)
        val rec= view.findViewById<RecyclerView>(R.id.saturday_rec)
        val adapter= SportSportsAdapter(requireContext(),isAdmin,requireActivity(),6)
        adapter.getData(6)
        rec.adapter=adapter
        rec.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= rec.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        return view
    }


}