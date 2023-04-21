package com.example.residence_app.adapters

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.FoundObjects
import com.example.residence_app.fragments.LostObjects

class ObjectsTabsAdapter(var c:Context,fm:FragmentManager?,val request:Int): FragmentPagerAdapter(fm!!) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

when(position){



    0 ->{

        val fragment = FoundObjects()
        fragment.request=request
        return fragment
    }

    1 -> {
        val fragment = LostObjects()
        fragment.request=request
        return fragment
    }
    else -> {
        return getItem(position)
    }
    }


    }
}