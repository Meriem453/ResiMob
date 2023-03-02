package com.example.residence_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.FootballInternational
import com.example.residence_app.fragments.FootballNational

class FootballTabsAdapter( fm:FragmentManager,val totalTabs:Int) :FragmentPagerAdapter(fm){
    override fun getCount(): Int {
       return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FootballNational()
            }
            1 -> {
                FootballInternational()
            }else -> getItem(position)
        }
    }
}