package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.residence_app.adapters.ObjectsTabsAdapter
import com.google.android.material.tabs.TabLayout

class ObjectAdminActivity : BaseActivity() {

    lateinit var tabs:TabLayout
    lateinit var vp:ViewPager
    lateinit var adapter: ObjectsTabsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_admin)
supportActionBar?.hide()
        tabs = findViewById(R.id.tabLayout_admin_objects)
        vp = findViewById(R.id.view_pager_admin_objects)
        setupVp()

    }
    fun setupVp(){
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.found_objects)))
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.lost_objects)))
        adapter= ObjectsTabsAdapter(supportFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,0,baseContext)
        vp.adapter=adapter


        vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // vp.adapter=ObjectsTabsAdapter(requireContext(),fragmentManager)
                vp.currentItem=tab!!.position


            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //vp.adapter=ObjectsTabsAdapter(requireContext(),fragmentManager)
                vp.currentItem=tab!!.position

            }

        })
    }
}