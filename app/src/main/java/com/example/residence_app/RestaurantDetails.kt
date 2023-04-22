package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.adapters.RestaurantTabsAdapter
import com.example.residence_app.databinding.ActivityRestaurantDetailsBinding
import com.google.android.material.tabs.TabLayout

class RestaurantDetails : BaseActivity() {

    lateinit var binding:ActivityRestaurantDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val tabs=binding.tabLayoutRestaurant
        val viewpager=binding.viewPagerRestaurant
        initTabs(tabs,viewpager)



    }

    private fun initTabs(tabs: TabLayout, viewpager: ViewPager) {
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.timings)))
        tabs.addTab(tabs.newTab().setText(baseContext.resources.getString(R.string.programme)))
        tabs.tabGravity=TabLayout.GRAVITY_FILL
        val adapter=RestaurantTabsAdapter(baseContext,supportFragmentManager,tabs.tabCount)
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