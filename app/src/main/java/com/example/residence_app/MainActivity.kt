package com.example.residence_app


import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.residence_app.databinding.ActivityMainBinding
import com.example.residence_app.fragments.HomeFragment
import com.example.residence_app.fragments.LostFragment
import com.example.residence_app.fragments.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavBar : BottomNavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var binding : ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        bottomNavBar = findViewById(R.id.bottomNavigationBar)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {

                    loadFragment(HomeFragment())
                    true
                }
                R.id.lost_screen -> {
                    loadFragment(LostFragment())
                    true
                }
                R.id.notification_screen -> {
                    loadFragment(NotificationsFragment())
                    true
                }


                else -> {true}
            }
        }
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.nav_open,
                R.string.nav_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home_screen -> {
                        bottomNavBar.selectedItemId = R.id.home_screen
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.lost_screen -> {
                        bottomNavBar.selectedItemId = R.id.lost_screen
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true

                    }
                    R.id.notification_screen -> {
                        bottomNavBar.selectedItemId = R.id.notification_screen
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                }
                true
            }

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragments_adapter,fragment)
        transaction.commit()
    }
}
