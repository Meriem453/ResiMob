package com.example.residence_app.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.BathroomBoysTiming
import com.example.residence_app.fragments.BathroomGirlsTiming
import com.example.residence_app.fragments.GymBoysTiming
import com.example.residence_app.fragments.GymGirlsTiming

class GymTabsAdapter (var c: Context, fm: FragmentManager, var totalTabs:Int):
    FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                GymGirlsTiming()
            }
            1 -> {
                GymBoysTiming()
            }else -> getItem(position)
        }
    }
}