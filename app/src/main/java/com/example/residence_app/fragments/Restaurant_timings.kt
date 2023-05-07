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
import com.example.residence_app.adapters.RestaurantTimingAdapter


class Restaurant_timings : Fragment() {
var isAdmin=false
lateinit var adapter: RestaurantTimingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_restaurant_timings, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.resturant_timing_rec)
        initRecyclerView(recycler)
        val swipe=view.findViewById<SwipeRefreshLayout>(R.id.resto_t_swipe)
        swipe.setOnRefreshListener {
            swipe.setRefreshing(false)
            adapter.getData()}
        return view

    }


private fun initRecyclerView(recycler: RecyclerView){
    recycler.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
    var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
    layoutManager.setReverseLayout(true);
    layoutManager.setStackFromEnd(true);
     adapter=RestaurantTimingAdapter(requireContext(),isAdmin,requireActivity())
    adapter.getData()
    recycler.adapter=adapter
}

}