package com.example.residence_app

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text
import java.io.File

class HomeUserActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var userImage: ImageView
    private lateinit var tUserEmail: TextView
    private lateinit var tUserName: TextView
    private lateinit var tRoom: TextView
    private lateinit var tGoodday : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)

        db = FirebaseFirestore.getInstance()
        tUserName = findViewById(R.id.user_name)
        tUserEmail = findViewById(R.id.user_eml)
        tRoom = findViewById(R.id.user_room)
        tGoodday = findViewById(R.id.good_day)
        userImage = findViewById(R.id.imageView3)



        var documentReference : DocumentReference
        //var uid : String = FirebaseAuth.getInstance().currentUser!!.uid
        var  uid :String = "WR5P8YBpxLXC68fuNgzFu5OoXSg2"
        val storageReference = FirebaseStorage.getInstance().reference.child("images/$uid.jpg")
        documentReference = db.collection("user").document(uid)
        documentReference.get().addOnCompleteListener {
            var name :String = it.result!!.data?.getValue("lname").toString().trim()
            var email : String = it.result!!.data?.getValue("email").toString().trim()
            var room : String = it.result!!.data?.getValue("room").toString().trim()
            tUserName.setText(name)
            tUserEmail.setText(email)
            tRoom.setText(room)
            tGoodday.setText("Hi! "+name)

        }
        val localfile = File.createTempFile("tempimage","jpg")
        storageReference.getFile(localfile).addOnCompleteListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            userImage.setImageBitmap(bitmap)
        }

    }
}