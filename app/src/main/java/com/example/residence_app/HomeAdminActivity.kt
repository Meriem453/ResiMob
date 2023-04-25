package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.residence_app.Interfaces.LogoutInterface
import com.example.residence_app.databinding.ActivityHomeAdminBinding
import com.example.residence_app.dialogues.AddLostObject
import com.example.residence_app.dialogues.AddNotification
import com.example.residence_app.dialogues.ChangeLanguage
import com.example.residence_app.dialogues.LogoutFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeAdminActivity : BaseActivity(),LogoutInterface {
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

        val users=findViewById<CardView>(R.id.admin_users)
        val feedback = findViewById<CardView>(R.id.admin_feedback)
        val problem = findViewById<CardView>(R.id.admin_problem)
        val objects=findViewById<CardView>(R.id.admin_obj)
        val notif = findViewById<CardView>(R.id.admin_notif)

        open.setOnClickListener {
            drawer.open()
        }
        binding.adminTime.setOnClickListener {

        }
        notif.setOnClickListener {
           startActivity(Intent(baseContext,NotificationAdminActivity::class.java))
        }
        objects.setOnClickListener {
           startActivity(Intent(baseContext,ObjectAdminActivity::class.java))
        }

        users.setOnClickListener {
            val intent=Intent(baseContext,Users::class.java)
            startActivity(intent)
        }
        feedback.setOnClickListener {
           startActivity(Intent(baseContext,FeedbackAdminActivity::class.java))
        }
        problem.setOnClickListener {
            startActivity(Intent(baseContext,ProblemAdminAcivity::class.java))
        }

        navview.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.admin_language -> {
                 ChangeLanguage().show(supportFragmentManager,"ll")

                    true
                }
                R.id.admin_theme -> {
                    true
                }
                R.id.admin_privacy -> {
                    true
                }
                R.id.admin_logout -> {
                   LogoutFragment(this).show(supportFragmentManager,"logout")

                    true
                }
                else -> {false}
            }

        }

    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1)
    }
}