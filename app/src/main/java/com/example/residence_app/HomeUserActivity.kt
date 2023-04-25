package com.example.residence_app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.residence_app.databinding.ActivityHomeUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text
import java.io.File

class HomeUserActivity : BaseActivity() {
    private var db = Firebase.firestore
    private lateinit var userImage: ImageView
    private lateinit var tUserEmail: TextView
    private lateinit var tUserName: TextView
    private lateinit var tRoom: TextView
    private lateinit var tGoodday : TextView
    private lateinit var binding: ActivityHomeUserBinding
    private lateinit var restaurant: CardView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)
        supportActionBar?.hide()
        binding=ActivityHomeUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        tUserName = findViewById(R.id.user_name)
        tUserEmail = findViewById(R.id.user_eml)
        tRoom = findViewById(R.id.user_room)
        tGoodday = findViewById(R.id.good_day)
        userImage = findViewById(R.id.imageView3)



binding.restaurant.setOnClickListener {
    val intent=Intent(baseContext,RestaurantDetails::class.java)
    startActivity(intent)

}
        binding.doctor.setOnClickListener {
            val intent=Intent(baseContext,DoctorDetails::class.java)
            startActivity(intent)
        }
binding.administration.setOnClickListener {
    val intent=Intent(baseContext,AdministrationDetails::class.java)
    startActivity(intent)
}
        binding.bathroom.setOnClickListener {
            startActivity(Intent(baseContext,BathroomDetails::class.java))
        }
        binding.gym.setOnClickListener {
            startActivity(Intent(baseContext,GymDetails::class.java))
        }
        binding.sport.setOnClickListener {
            startActivity(Intent(baseContext,SportDetails::class.java))
        }
        binding.buttonFeedback.setOnClickListener {
            startActivity(Intent(baseContext,FeedbackActivity::class.java))
        }
        binding.buttonProblem.setOnClickListener {
            startActivity(Intent(baseContext,Problem::class.java))
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



    }
}