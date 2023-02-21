package com.example.residence_app

import android.content.Intent
import android.net.Uri
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
    private lateinit var progressBar: ProgressBar
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var imageUri : Uri?=null


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
        progressBar = findViewById(R.id.progress_bar)

        image.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,1)
        }


        addUserBtn.setOnClickListener {
            //progressBar.visibility = View.VISIBLE
            val sFname = etFirstName.text.toString().trim()
            val sLname = etLastName.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()
            val sCpassword = etCpassword.text.toString().trim()



            if (sFname.isEmpty() || sLname.isEmpty() || sEmail.isEmpty() || sPassword.isEmpty() || sCpassword.isEmpty() || sPassword.length<6 || imageUri == null) {
                if (sFname.isEmpty()){
                    etFirstName.error = "Enter the first name"
                }
                if (sLname.isEmpty()){
                    etLastName.error = "Enter the last name"
                }
                if (sEmail.isEmpty()){
                    etEmail.error = "Enter the email"
                }
                if (sFname.isEmpty() ){
                    etPassword.error = "Enter the password"
                }
                if (sFname.isEmpty()){
                    etCpassword.error = "Confirm the password"
                }
                if (sPassword.length < 6){
                    etPassword.error = "you must enter more than 5 letters"
                }
                if (imageUri == null) {
                    Toast.makeText(this, "Enter the image!", Toast.LENGTH_SHORT).show()
                }
                    Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()

                //progressBar.visibility = View.GONE
            }else{if (!sEmail.matches(emailPattern.toRegex())) {
                etEmail.error = "Enter valid email"
            }else{
                if (sCpassword == sPassword) {
                    val userMap = hashMapOf(
                        "fname" to sFname,
                        "lname" to sLname,
                        "email" to sEmail,
                        "password" to sPassword,
                        "image" to imageUri,
                        "isadmin" to false,

                        )


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener{
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid
                    db.collection("user").document(userId).set(userMap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()
                            etFirstName.text.clear()
                            etLastName.text.clear()
                            etEmail.text.clear()
                            etPassword.text.clear()
                            etCpassword.text.clear()


                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    }}
                }else{
                    etCpassword.error = "Password Invalid"
                    Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()

                }
                //progressBar.visibility = View.GONE
            }}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!=null){
            if (data.data!=null){
                imageUri=data.data!!
                image.setImageURI(imageUri)
            }
        }
    }
}
