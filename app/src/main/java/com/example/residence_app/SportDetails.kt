package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.residence_app.adapters.SportRoomTabsAdapter
import com.example.residence_app.adapters.SportSportsAdapter
import com.example.residence_app.databinding.ActivitySportDetailsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class SportDetails : BaseActivity() {
    lateinit var binding:ActivitySportDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_details)

        supportActionBar?.hide()

        binding=ActivitySportDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.sun)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.mon)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.tus)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.wed)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.thu)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.fri)))
        binding.sportTab.addTab(binding.sportTab.newTab().setText(resources.getString(R.string.sat)))

        binding.sportViewpager.adapter = SportRoomTabsAdapter(supportFragmentManager,baseContext,intent.getBooleanExtra("isAdmin",false))
        binding.sportViewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.sportTab))
        binding.sportTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                binding.sportViewpager.currentItem = tab!!.position

            }

            override fun onTabUnselected(tab: Tab?) {}

            override fun onTabReselected(tab: Tab?) {}
        })



    }



}