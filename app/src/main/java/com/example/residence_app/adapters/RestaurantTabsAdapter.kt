package com.example.residence_app.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.Restaurant_timings
import com.example.residence_app.fragments.Restaurant_programme

internal class RestaurantTabsAdapter(var c:Context, fm:FragmentManager, var totalTabs:Int,var isAdmin:Boolean):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {


    override fun getCount(): Int {
     return totalTabs
    }

    override fun getItem(position: Int): Fragment {
         when(position){
            0 -> {
                val fragment=Restaurant_timings()
                fragment.isAdmin=isAdmin
                return fragment
            }
            1 -> {
               val fragment = Restaurant_programme()
                fragment.isAdmin=isAdmin
                return fragment
            }else -> return getItem(position)
        }
    }

}