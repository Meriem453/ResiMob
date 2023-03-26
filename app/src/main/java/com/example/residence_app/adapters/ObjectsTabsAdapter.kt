package com.example.residence_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.FoundObjects
import com.example.residence_app.fragments.LostObjects

class ObjectsTabsAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
return when(position){
    0 -> FoundObjects()

    1 -> LostObjects()

    else -> getItem(position)
}
    }
}