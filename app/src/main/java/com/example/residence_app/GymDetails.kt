package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.adapters.*
import com.example.residence_app.databinding.ActivityGymDetailsBinding
import com.google.android.material.tabs.TabLayout

class GymDetails : AppCompatActivity() {

    lateinit var binding: ActivityGymDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym_details)
        supportActionBar?.hide()

        binding= ActivityGymDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.blue_button))
        supportActionBar?.title=resources.getString(R.string.gym)

        val tabs=binding.tabLayoutGym
        val pager=binding.viewPagerGym
        initTabs(tabs,pager)
    }
    private fun initTabs(tabs: TabLayout, viewpager: ViewPager) {
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.girls)))
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.boys)))
        tabs.tabGravity= TabLayout.GRAVITY_FILL
        val adapter= GymTabsAdapter(baseContext,supportFragmentManager,tabs.tabCount)
        viewpager.adapter=adapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager.currentItem=tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}


        })
    }

}