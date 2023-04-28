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
import com.example.residence_app.dialogues.AddFoundObject

class FoundObjects : Fragment() {
      var request=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_found_objects, container, false)
        val rec=view.findViewById<RecyclerView>(R.id.foundObj_rec)
        val adapter=ObjectsAdapter(requireContext(),request,requireActivity().supportFragmentManager)
        adapter.getFonderData()
        rec.adapter=adapter
        rec.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= rec.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        val add = view.findViewById<Button>(R.id.foundObj_add)
        add.setOnClickListener {
            AddFoundObject().show(requireActivity().supportFragmentManager,"bla bla bla")
        }
        return view
    }


}