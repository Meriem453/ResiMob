package com.example.residence_app.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.residence_app.*
import com.example.residence_app.databinding.ActivityHomeUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class HomeFragment : Fragment() {
    private var db = Firebase.firestore
    private lateinit var userImage: ImageView
    private lateinit var tUserEmail: TextView
    private lateinit var tUserName: TextView
    private lateinit var tRoom: TextView
    private lateinit var tGoodday : TextView

    private lateinit var restaurant: CardView
    private lateinit var doctor: CardView
    private lateinit var administration: CardView
    private lateinit var bathroom: CardView
    private lateinit var gym: CardView
    private lateinit var sport: CardView






    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_home, container, false)
        db = FirebaseFirestore.getInstance()
        tUserName = view.findViewById(R.id.user_namefr)
        tUserEmail = view.findViewById(R.id.user_emlfr)
        tRoom = view.findViewById(R.id.user_roomfr)
        tGoodday = view.findViewById(R.id.good_dayfr)
        userImage = view.findViewById(R.id.imageView3fr)
        restaurant = view.findViewById(R.id.restaurantfr)
        sport = view.findViewById(R.id.sportfr)
        bathroom = view.findViewById(R.id.bathroomfr)
        gym  = view.findViewById(R.id.gymfr)
        doctor = view.findViewById(R.id.doctorfr)
        administration = view.findViewById(R.id.administrationfr)

        restaurant.setOnClickListener {
            val intent= Intent(this.context, RestaurantDetails::class.java)
            intent.putExtra("isAdmin",false)

            startActivity(intent)

        }
        doctor.setOnClickListener {
            val intent= Intent(this.context, DoctorDetails::class.java)
            intent.putExtra("isAdmin",false)
            startActivity(intent)
        }
        administration.setOnClickListener {
            val intent= Intent(this.context, AdministrationDetails::class.java)
            intent.putExtra("isAdmin",false)
            startActivity(intent)
        }
        bathroom.setOnClickListener {
            val intent = Intent(this.context,BathroomDetails::class.java)
            intent.putExtra("isAdmin",false)
            startActivity(intent)
        }
        gym.setOnClickListener {
            val intent = Intent(this.context,GymDetails::class.java)
            intent.putExtra("isAdmin",false)
            startActivity(intent)        }
        sport.setOnClickListener {
            val intent = Intent(this.context,SportDetails::class.java)
            intent.putExtra("isAdmin",false)
            startActivity(intent)
        }
        var documentReference : DocumentReference
        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        val storageReference = FirebaseStorage.getInstance().reference.child("images/$uid.jpg")
        //user name...
        documentReference = db.collection("user").document(uid)
        documentReference.get().addOnCompleteListener {
            var name :String = it.result!!.data?.getValue("lname").toString().trim()
            var email : String = it.result!!.data?.getValue("email").toString().trim()
            var room : String = it.result!!.data?.getValue("room").toString().trim()
            var imgurl : String = it.result!!.data?.getValue("image").toString().trim()
            tUserName.setText(name)
            tUserEmail.setText(email)
            tRoom.setText(room)
            tGoodday.text  = String.format(resources.getString(R.string.hi),name)
            //image
            Glide.with(this).load(imgurl).into(userImage)
        }
        
        return view
    }



}