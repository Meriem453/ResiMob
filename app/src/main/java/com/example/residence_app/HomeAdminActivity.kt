package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.residence_app.databinding.ActivityHomeAdminBinding
import com.google.android.material.navigation.NavigationView

class HomeAdminActivity : AppCompatActivity() {
    lateinit var drawer:DrawerLayout
    lateinit var open:ImageView
    lateinit var binding:ActivityHomeAdminBinding
    lateinit var navview:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setContentView(R.layout.activity_home_admin)
        drawer=findViewById(R.id.admin_drawerlayout)
        open=findViewById(R.id.admin_navdrawer)
        navview=findViewById(R.id.admin_navview)


        open.setOnClickListener {
            drawer.open()
        }
        binding.adminTime.setOnClickListener {

        }
        binding.adminNotif.setOnClickListener {

        }
        binding.adminObj.setOnClickListener {

        }
        binding.adminUsers.setOnClickListener {

        }
        binding.adminFeedback.setOnClickListener {

        }
        binding.adminProblem.setOnClickListener {

        }

        navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.admin_home -> {
                    true
                }
                R.id.admin_language -> {
                    true
                }
                R.id.admin_theme -> {
                    true
                }
                R.id.admin_privacy -> {
                    true
                }
                R.id.admin_logout -> {
                    true
                }
                else -> {false}
            }

        }

    }
}