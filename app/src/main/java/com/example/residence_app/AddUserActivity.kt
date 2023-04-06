package com.example.residence_app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.residence_app.data.UserInfo
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class AddUserActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCpassword: EditText
    private lateinit var etRoom: EditText
    private lateinit var addUserBtn: Button
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var imageUri : Uri?=null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        supportActionBar?.hide()
        etFirstName = findViewById(R.id.first_name)
        etLastName = findViewById(R.id.last_name)
        etEmail = findViewById(R.id.email_address)
        etPassword = findViewById(R.id.password)
        etCpassword = findViewById(R.id.confirm_password)
        etRoom = findViewById(R.id.room)
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
            progressBar.visibility = View.VISIBLE
            val sFname = etFirstName.text.toString().trim()
            val sLname = etLastName.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()
            val sCpassword = etCpassword.text.toString().trim()
            val sRoom = etRoom.text.toString().trim()


            if (sFname.isEmpty() || sLname.isEmpty() || sEmail.isEmpty() || sPassword.isEmpty() || sCpassword.isEmpty() || sPassword.length<6 || imageUri == null || sRoom.isEmpty()) {
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
                if (sRoom.isEmpty()){
                    etRoom.error = "Enter the Room details"
                }
                    Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE

            }else{
                if (!sEmail.matches(emailPattern.toRegex())) {
                    progressBar.visibility = View.GONE
                etEmail.error = "Enter valid email"
            }else{FirebaseAuth.getInstance().fetchSignInMethodsForEmail(sEmail).addOnCompleteListener{
                check-> if(check.getResult().equals(true)){
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Email already exist!", Toast.LENGTH_SHORT).show()
                }else{


                if (sCpassword == sPassword) {
                    val userMap = hashMapOf(
                        "fname" to sFname,
                        "lname" to sLname,
                        "email" to sEmail,
                        "password" to sPassword,
                        "image" to imageUri,
                        "isadmin" to false,
                        "room" to sRoom,

                        )

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener{
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid
                        uploadImageToFirebase(imageUri!!,userId)
                    db.collection("user").document(userId).set(userMap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()
                            etFirstName.text.clear()
                            etLastName.text.clear()
                            etEmail.text.clear()
                            etPassword.text.clear()
                            etCpassword.text.clear()
                            etRoom.text.clear()
                            image.setImageResource(0)
                            progressBar.visibility = View.GONE

                    }.addOnFailureListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                    }
                    }else{
                    progressBar.visibility = View.GONE
                    etCpassword.error = "Password Invalid"
                    Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()

                }

            }}}
            }

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
    private fun uploadImageToFirebase(fileUri: Uri,userId: String) {
        if (fileUri != null) {
            val fileName = userId +".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

            refStorage.putFile(fileUri)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                        }
                    })

                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
        }
    }
}

