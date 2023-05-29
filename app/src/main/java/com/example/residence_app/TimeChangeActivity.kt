package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

private lateinit var restaurant: CardView
private lateinit var doctor: CardView
private lateinit var administration: CardView
private lateinit var bathroom: CardView
private lateinit var gym: CardView
private lateinit var sport: CardView


class TimeChangeActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_change)

        supportActionBar?.hide()

        restaurant = findViewById(R.id.restaurant_admin)
        sport = findViewById(R.id.sport_admin)
        bathroom = findViewById(R.id.bathroom_admin)
        gym  = findViewById(R.id.gym_admin)
        doctor = findViewById(R.id.doctor_admin)
        administration = findViewById(R.id.administration_admin)



        restaurant.setOnClickListener {
            val intent= Intent(baseContext, RestaurantDetails::class.java)
            intent.putExtra("isAdmin",true)
            startActivity(intent)


        }
        doctor.setOnClickListener {
            val intent= Intent(baseContext, DoctorDetails::class.java)
            intent.putExtra("isAdmin",true)

            startActivity(intent)
        }
        administration.setOnClickListener {
            val intent= Intent(baseContext, AdministrationDetails::class.java)
            intent.putExtra("isAdmin",true)
            startActivity(intent)
        }
        bathroom.setOnClickListener {
            val intent= Intent(baseContext, BathroomDetails::class.java)
            intent.putExtra("isAdmin",true)
            startActivity(intent)
        }
        gym.setOnClickListener {
            val intent= Intent(baseContext, GymDetails::class.java)
            intent.putExtra("isAdmin",true)
            startActivity(intent)
        }
        sport.setOnClickListener {
            val intent= Intent(baseContext, SportDetails::class.java)
            intent.putExtra("isAdmin",true)
            startActivity(intent)
        }

        val lin1=findViewById<LinearLayout>(R.id.lin1)
        val lin2=findViewById<LinearLayout>(R.id.lin2)
        val lin3=findViewById<LinearLayout>(R.id.lin3)
        val grid=findViewById<LinearLayout>(R.id.gridLayout)


        when(intent.getIntExtra("admin",0)){
            2 ->{restaurant.visibility=View.GONE
                 bathroom.visibility=View.GONE
                 administration.visibility=View.GONE
            }
            3->{
                restaurant.visibility=View.GONE
                administration.visibility=View.GONE
                restaurant.visibility=View.GONE
                gym.visibility=View.GONE
                sport.visibility=View.GONE
                lin1.visibility=View.GONE
                lin2.visibility=View.GONE
                grid.weightSum= 3F

            }
            4->{
                bathroom.visibility=View.GONE
                administration.visibility=View.GONE
                doctor.visibility=View.GONE
                gym.visibility=View.GONE
                sport.visibility=View.GONE
            }


        }

    }


}