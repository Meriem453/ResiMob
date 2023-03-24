package com.example.residence_app.adapters

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.residence_app.fragments.SportDetailsFragment

class SportRoomTabsAdapter(fm:FragmentManager,var c:Context): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 7
    }

    override fun getItem(position: Int): Fragment {
        return SportDetailsFragment()


}
}