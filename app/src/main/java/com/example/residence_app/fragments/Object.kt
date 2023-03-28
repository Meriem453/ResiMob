package com.example.residence_app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.R
import com.example.residence_app.adapters.ObjectsTabsAdapter
import com.google.android.material.tabs.TabLayout


class Object : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
val view = inflater.inflate(R.layout.fragment_object, container, false)
        val tabs= view.findViewById<TabLayout>(R.id.tabLayout_objects)
        val vp=view.findViewById<ViewPager>(R.id.view_pager_objects)
        vp.adapter=ObjectsTabsAdapter(requireContext(),fragmentManager)
        vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.found_objects)))
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.lost_objects)))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
                vp.currentItem=tab!!.position


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
return view
    }


}