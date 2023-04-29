package com.example.residence_app.adapters

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.*

class SportRoomTabsAdapter(fm:FragmentManager,var c:Context,val isAdmin:Boolean): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 7
    }

    override fun getItem(position: Int): Fragment {
         when (position){
            0 -> {
                val fragment=Sunday()
                fragment.isAdmin=isAdmin
                return fragment
            }
            1 -> {
                val fragment=Monday()
                fragment.isAdmin=isAdmin
                return fragment            }
             2 -> {
                 val fragment=Tusday()
                 fragment.isAdmin=isAdmin
                 return fragment
            } 3 -> {
             val fragment=Wednasday()
             fragment.isAdmin=isAdmin
             return fragment
            } 4 -> {
             val fragment=Thusday()
             fragment.isAdmin=isAdmin
             return fragment
            } 5 -> {
             val fragment=Friday()
             fragment.isAdmin=isAdmin
             return fragment
            } 6 -> {
             val fragment=Saturday()
             fragment.isAdmin=isAdmin
             return fragment
            }

            else -> { return getItem(position)}
        }



}
}