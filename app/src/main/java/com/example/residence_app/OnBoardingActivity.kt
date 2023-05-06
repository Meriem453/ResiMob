package com.example.residence_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.residence_app.adapters.OnBoardingItemsAdapter
import com.example.residence_app.data.OnBoardingItemsData
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingActivity : BaseActivity() {
    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_avtivity)
        supportActionBar?.hide()
        setOnboardingItems()

    }
        private fun setOnboardingItems(){
         onBoardingItemsAdapter = OnBoardingItemsAdapter(
             listOf(
                 OnBoardingItemsData(
                     resources.getString(R.string.OnBoardingMainText1),resources.getString(R.string.OnBoardingSecondText1),R.drawable.obimage1
                 ),
                 OnBoardingItemsData(
                     resources.getString(R.string.OnBoardingMainText2),resources.getString(R.string.OnBoardingSecondText2),R.drawable.obimage2
                 ),
                 OnBoardingItemsData(
                     resources.getString(R.string.OnBoardingMainText3),resources.getString(R.string.OnBoardingSecondText3),R.drawable.obimage3
                 )
             )
         )
            fun navigateActivity(){
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                finish()
            }

            val ViewPager = findViewById<ViewPager2>(R.id.viewPagerID)
            ViewPager.adapter = onBoardingItemsAdapter
            val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
            dotsIndicator.attachTo(ViewPager)
            findViewById<Button>(R.id.nextbutton).setOnClickListener {
                if (ViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount){
                    ViewPager.currentItem += 1
                }else{
                    val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                    val editor = sharedPreference.edit()
                    editor.putString("isChecked","true")
                    editor.apply()
                    navigateActivity()
                }
            }
            (ViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

    }
