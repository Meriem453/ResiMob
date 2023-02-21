package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddUserActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCpassword: EditText
    private lateinit var addUserBtn: Button
    private lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        etFirstName = findViewById(R.id.first_name)
        etLastName = findViewById(R.id.last_name)
        etEmail = findViewById(R.id.email_address)
        etPassword = findViewById(R.id.password)
        etCpassword = findViewById(R.id.confirm_password)
        addUserBtn = findViewById(R.id.btn_add_user)
        image = findViewById(R.id.imageView)

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { image.setImageURI(it) }
        )
        image.setOnClickListener {
            galleryImage.launch("image/*")
        }


        addUserBtn.setOnClickListener {

            val sFname = etFirstName.text.toString().trim()
            val sLname = etLastName.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()
            val sCpassword = etCpassword.text.toString().trim()



            if (sFname.isEmpty() || sLname.isEmpty() || sEmail.isEmpty() || sPassword.isEmpty() || sCpassword.isEmpty()) {
                if (sFname.isEmpty()){
                    etFirstName.error = "Enter the first name"
                }
                if (sLname.isEmpty()){
                    etFirstName.error = "Enter the last name"
                }
                if (sEmail.isEmpty()){
                    etFirstName.error = "Enter the email"
                }
                if (sFname.isEmpty()){
                    etFirstName.error = "Enter the password"
                }
                if (sFname.isEmpty()){
                    etFirstName.error = "Confirm the password"
                }
                Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()
            }else{
                if (sCpassword == sPassword) {
                    val userMap = hashMapOf(
                        "fname" to sFname,
                        "lname" to sLname,
                        "email" to sEmail,
                        "password" to sPassword,
                        "image" to "image",
                        "isadmin" to false,

                        )



                    db.collection("user").document("1").set(userMap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()


                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    etCpassword.error = "Password Invalid"
                    Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}
