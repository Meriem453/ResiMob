package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.residence_app.R
import com.example.residence_app.adapters.RestaurantProgrammeAdapter


class Restaurant_programme : Fragment() {
    var isAdmin=false
lateinit var adapter:RestaurantProgrammeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_retaurant_programme, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.restaurant_programme_rec)
        initRecyclerView(recycler)
        val swipe=view.findViewById<SwipeRefreshLayout>(R.id.resto_p_swipe)
        swipe.setOnRefreshListener {
            swipe.setRefreshing(false)
           }
        return view

    }

    private fun initRecyclerView(recycler: RecyclerView) {
        recycler.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
         adapter=RestaurantProgrammeAdapter(requireContext(),isAdmin,requireActivity())
        recycler.adapter=adapter
    }


}