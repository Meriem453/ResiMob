package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        val eclipse=findViewById<ImageView>(R.id.eclipse)
        val card =findViewById<CardView>(R.id.cardView)
        val login=findViewById<Button>(R.id.btn_login)
       eclipse.bringToFront()
        eclipse.invalidate()
        card.invalidate()

        login.setOnClickListener {
            val intent=Intent(this,AddUserActivity::class.java)
            startActivity(intent)
        }



    }
}