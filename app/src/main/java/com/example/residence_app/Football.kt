package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.adapters.FootballTabsAdapter
import com.example.residence_app.databinding.ActivityFootballBinding
import com.google.android.material.tabs.TabLayout

class Football : AppCompatActivity() {
    lateinit var binding:ActivityFootballBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football)
        binding=ActivityFootballBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = (resources.getString(R.string.football))
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.blue_botton))

        initTabs(binding.footballTab,binding.footballViewpager)

    }

    private fun initTabs(tabs: TabLayout, viewpager: ViewPager) {
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.national)))
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.international)))
        tabs.tabGravity= TabLayout.GRAVITY_FILL
        val adapter= FootballTabsAdapter(supportFragmentManager,tabs.tabCount)
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