package com.example.residence_app


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment

import com.example.residence_app.fragments.HomeFragment
import com.example.residence_app.fragments.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MainActivity : BaseActivity() {
    lateinit var bottomNavBar : BottomNavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var db = Firebase.firestore
    //lateinit var binding : ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView:NavigationView
    lateinit var open:ImageView
//    lateinit var userName:TextView
//    lateinit var roomDraw:TextView
//    lateinit var imageNav:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        loadFragment(NotificationsFragment())
        drawerLayout = findViewById(R.id.userdrawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        bottomNavBar = findViewById(R.id.bottomNavigationBar)
        navView=findViewById<NavigationView>(R.id.nav_view)
        open = findViewById(R.id.user_image)
        val imageNav = findViewById<ImageView>(R.id.imageNav_ID)
        val roomDraw = findViewById<TextView>(R.id.roomNav_ID)
        val userName = findViewById<TextView>(R.id.nameNav_ID)
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
                else -> {loadFragment(NotificationsFragment())
                    false}
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
                    R.id.LogOut-> {
                        FirebaseAuth.getInstance().signOut()
                        startActivity(Intent(baseContext,LoginActivity::class.java))
                    }
                }
                true
            }
        open.setOnClickListener { drawerLayout.open() }
        val documentReference : DocumentReference
        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        val storageReference = FirebaseStorage.getInstance().reference.child("images/$uid.jpg")
        //user name...
        documentReference = db.collection("user").document(uid)
        documentReference.get().addOnCompleteListener {
            val name :String = it.result!!.data?.getValue("lname").toString().trim()
            var email : String = it.result!!.data?.getValue("email").toString().trim()
            val room : String = it.result!!.data?.getValue("room").toString().trim()
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
