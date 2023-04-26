package com.example.residence_app


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.residence_app.dialogues.LogoutFragment

import com.example.residence_app.fragments.HomeFragment
import com.example.residence_app.fragments.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {
    lateinit var bottomNavBar : BottomNavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    //lateinit var binding : ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        bottomNavBar = findViewById(R.id.bottomNavigationBar)
        navView=findViewById<NavigationView>(R.id.nav_view)


        bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.lost_screen -> {

                     val fragment=com.example.residence_app.fragments.Object()
                    loadFragment(fragment)
                    true
                }
                R.id.notification_screen -> {
                    loadFragment(NotificationsFragment())
                    true
                }


                else -> {false}
            }
        }

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
                    R.id.problem_screen -> {
                        startActivity(Intent(baseContext,Problem::class.java))
                    }
                    R.id.feedback_screen -> {
                        startActivity(Intent(baseContext,FeedbackActivity::class.java))
                    }
                    R.id.LogOut -> {

                            FirebaseAuth.getInstance().signOut()
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }, 1)

                    }
                }
                true
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
