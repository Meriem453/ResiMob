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
import com.example.residence_app.adapters.TimingAdapter


class BathroomGirlsTiming : Fragment() {
    var isAdmin=false
    lateinit var adapter:TimingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_bathroom_girls_timing, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.bathroom_girlstiming_rec)
        initRecyclerView(recycler)
        val swipe=view.findViewById<SwipeRefreshLayout>(R.id.bath_g_swipe)
        swipe.setOnRefreshListener {
            swipe.setRefreshing(false)
            adapter.getBathroomGirlsTimingData()}
        return view
    }

    private fun initRecyclerView(recycler: RecyclerView) {
   adapter=TimingAdapter(requireContext(),requireActivity(), isAdmin )
        adapter.getBathroomGirlsTimingData()
        recycler.adapter=adapter
        recycler.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }

}