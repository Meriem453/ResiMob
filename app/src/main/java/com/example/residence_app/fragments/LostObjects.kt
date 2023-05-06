package com.example.residence_app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.Interfaces.AddObjectInterface
import com.example.residence_app.R
import com.example.residence_app.adapters.ObjectsAdapter
import com.example.residence_app.dialogues.AddLostObject


class LostObjects(val c:Context) : Fragment(),AddObjectInterface {
var request=0
    lateinit var adapter: ObjectsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_lost_objects, container, false)
        val rec =view.findViewById<RecyclerView>(R.id.lostObj_rec)
         adapter = ObjectsAdapter(requireContext(),request,requireActivity().supportFragmentManager)
        adapter.getLoserData()
        rec.adapter = adapter
        rec.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)


        val add = view.findViewById<Button>(R.id.lostObj_add)
        add.setOnClickListener {
            val addlost=AddLostObject(c,this)

           addlost.show(requireActivity().supportFragmentManager,"bla bla bla")
        }
        return view
    }

    override fun addObject() {
       // adapter.getLoserData()
    }


}