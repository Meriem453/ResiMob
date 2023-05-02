package com.example.residence_app.adapters

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.residence_app.fragments.FoundObjects
import com.example.residence_app.fragments.LostObjects

class ObjectsTabsAdapter( fm:FragmentManager, behavior:Int , val request:Int,val c:Context): FragmentStatePagerAdapter(fm,behavior) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

when(position){



    0 ->{

        val fragment = FoundObjects(c)
        fragment.request=request
        return fragment
    }

    1 -> {
        val fragment = LostObjects(c)
        fragment.request=request
        return fragment
    }
    else -> {
        return getItem(position)
    }
    }


    }
}