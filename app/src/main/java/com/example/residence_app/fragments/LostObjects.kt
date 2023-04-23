package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.ObjectsAdapter
import com.example.residence_app.dialogues.AddLostObject


class LostObjects : Fragment() {
var request=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_lost_objects, container, false)
        val rec =view.findViewById<RecyclerView>(R.id.lostObj_rec)
        val adapter = ObjectsAdapter(requireContext(),request,requireActivity().supportFragmentManager)
        adapter.getLoserData()
        rec.adapter = adapter
        rec.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        val add = view.findViewById<Button>(R.id.lostObj_add)
        add.setOnClickListener {
            AddLostObject().show(requireActivity().supportFragmentManager,"bla bla bla")
        }
        return view
    }


}