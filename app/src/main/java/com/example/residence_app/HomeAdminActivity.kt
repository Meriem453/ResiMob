package com.example.residence_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.residence_app.Interfaces.LogoutInterface
import com.example.residence_app.databinding.ActivityHomeAdminBinding
import com.example.residence_app.dialogues.ChangeLanguage
import com.example.residence_app.dialogues.LogoutFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeAdminActivity : BaseActivity(),LogoutInterface {
    lateinit var drawer:DrawerLayout
    lateinit var open:ImageView
    lateinit var binding:ActivityHomeAdminBinding
    lateinit var navview:NavigationView

    lateinit var users:CardView
    lateinit var feedback:CardView
    lateinit var problem:CardView
    lateinit var objects:CardView
    lateinit var notif:CardView
    lateinit var time:CardView

    lateinit var li1:LinearLayout
    lateinit var li2:LinearLayout
    lateinit var li3:LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setContentView(R.layout.activity_home_admin)

        val admin=intent.getIntExtra("admin",0)



        drawer=findViewById(R.id.admin_drawerlayout)
        open=findViewById(R.id.admin_navdrawer)
        navview=findViewById(R.id.admin_navview)

         users=findViewById<CardView>(R.id.admin_users)
         feedback = findViewById<CardView>(R.id.admin_feedback)
         problem = findViewById<CardView>(R.id.admin_problem)
         objects=findViewById<CardView>(R.id.admin_obj)
         notif = findViewById<CardView>(R.id.admin_notif)
         time=findViewById<CardView>(R.id.admin_time)

        li1=findViewById(R.id.li1)
        li2=findViewById(R.id.li2)
        li3=findViewById(R.id.li3)

        open.setOnClickListener {
            drawer.open()
        }
        time.setOnClickListener {
            val intent=Intent(baseContext,TimeChangeActivity::class.java)
            intent.putExtra("admin",admin)
              startActivity(intent)
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
                    if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)}
                    else{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)}
                    true
                }
                R.id.admin_privacy -> {
                    startActivity(Intent(baseContext,PrivacyPolicy::class.java))
                    true
                }
                R.id.admin_logout -> {
                   LogoutFragment(this).show(supportFragmentManager,"logout")

                    true
                }
                else -> false
            }

        }


        //getting the nmr of the admin
        when(admin){
            1 -> {
                objects.visibility= View.GONE
                notif.visibility=View.GONE
                time.visibility=View.GONE
                li1.visibility=View.GONE
            }
            2 -> {
                users.visibility=View.GONE
                objects.visibility=View.GONE
                li2.visibility=View.GONE
            }
            3 -> {
                users.visibility=View.GONE
            }
            4 -> {
                users.visibility=View.GONE
                objects.visibility=View.GONE
                li2.visibility=View.GONE

            }
            5 -> {
                users.visibility=View.GONE
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