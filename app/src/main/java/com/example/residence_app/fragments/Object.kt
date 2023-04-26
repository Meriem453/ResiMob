package com.example.residence_app.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.R
import com.example.residence_app.adapters.ObjectsTabsAdapter
import com.example.residence_app.fragments.Object
import com.google.android.material.tabs.TabLayout


class Object : Fragment() {
lateinit var vp: ViewPager
lateinit var adapter: ObjectsTabsAdapter
lateinit var tabs:TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
val view = inflater.inflate(R.layout.fragment_object, container, false)
        tabs= view.findViewById<TabLayout>(R.id.tabLayout_objects)
        vp=view.findViewById(R.id.view_pager_objects)
        setupVp()


return view
    }
fun setupVp(){
    tabs.addTab(tabs.newTab().setText(resources.getString(R.string.found_objects)))
    tabs.addTab(tabs.newTab().setText(resources.getString(R.string.lost_objects)))
    adapter=ObjectsTabsAdapter(childFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,1)
    vp.adapter=adapter

    vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            //vp.adapter=ObjectsTabsAdapter(requireContext(),fragmentManager)
            vp.currentItem=tab!!.position


        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {
            //vp.adapter=ObjectsTabsAdapter(requireContext(),fragmentManager)
            vp.currentItem=tab!!.position

        }

    })
}

}