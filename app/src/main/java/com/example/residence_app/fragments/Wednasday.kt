package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.residence_app.R
import com.example.residence_app.adapters.SportSportsAdapter


class Wednasday : Fragment() {

var isAdmin=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_wednasday, container, false)
        val rec= view.findViewById<RecyclerView>(R.id.wednesday_rec)
        val adapter= SportSportsAdapter(requireContext(),isAdmin,requireActivity(),3)
        adapter.getData(3)
        rec.adapter=adapter
        rec.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= rec.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        val swipe=view.findViewById<SwipeRefreshLayout>(R.id.wednesday_swipe)
        swipe.setOnRefreshListener {
            swipe.setRefreshing(false)
            adapter.getData(3)}
        return view
    }


}