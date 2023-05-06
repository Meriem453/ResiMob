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

class AddUserActivity :BaseActivity() {
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
                    etFirstName.error = resources.getString(R.string.please_enter_a_text)
                }
                if (sLname.isEmpty()){
                    etLastName.error = resources.getString(R.string.please_enter_a_text)
                }
                if (sEmail.isEmpty()){
                    etEmail.error = resources.getString(R.string.please_enter_a_text)
                }
                if (sFname.isEmpty() ){
                    etPassword.error = resources.getString(R.string.please_enter_a_text)
                }
                if (sFname.isEmpty()){
                    etCpassword.error = resources.getString(R.string.please_enter_a_text)
                }
                if (sPassword.length < 6){
                    etPassword.error = resources.getString(R.string.you_must_enter_more_than_5_letters)
                }
                if (imageUri == null) {
                    Toast.makeText(this, resources.getString(R.string.add_picture), Toast.LENGTH_SHORT).show()
                }
                if (sRoom.isEmpty()){
                    etRoom.error = resources.getString(R.string.please_enter_a_text)
                }
                    Toast.makeText(this, resources.getString(R.string.enter_valid_informations), Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE

            }else{
                if (!sEmail.matches(emailPattern.toRegex())) {
                    progressBar.visibility = View.GONE
                etEmail.error = resources.getString(R.string.enter_valid_email)
            }else{FirebaseAuth.getInstance().fetchSignInMethodsForEmail(sEmail).addOnCompleteListener{
                check-> if(check.getResult().signInMethods?.isEmpty() == false){
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, resources.getString(R.string.email_already_exist), Toast.LENGTH_SHORT).show()
                }else{


                if (sCpassword == sPassword) {
                    val adminid = FirebaseAuth.getInstance().currentUser!!.uid



                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener{
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid

                        if (imageUri!! != null) {
                            val fileName = userId +".jpg"
                            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

                            refStorage.putFile(imageUri!!)
                                .addOnSuccessListener(
                                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                                            val imageUrl = it.toString()
                                            val userMap = hashMapOf(
                                                "uid" to userId,
                                                "fname" to sFname,
                                                "lname" to sLname,
                                                "email" to sEmail,
                                                "password" to sPassword,
                                                "image" to imageUrl,
                                                "room" to sRoom,

                                                )
                                            db.collection("user").document(userId).set(userMap).addOnSuccessListener {
                                                Toast.makeText(this, resources.getString(R.string.added_succesfully), Toast.LENGTH_SHORT).show()
                                                db.collection("user").document(adminid).get().addOnCompleteListener{
                                                    val adminmail = it.result!!.data?.getValue("email").toString().trim()
                                                    val adminpass = it.result!!.data?.getValue("password").toString().trim()
                                                FirebaseAuth.getInstance().signInWithEmailAndPassword(adminmail,adminpass)}
                                                etFirstName.text.clear()
                                                etLastName.text.clear()
                                                etEmail.text.clear()
                                                etPassword.text.clear()
                                                etCpassword.text.clear()
                                                etRoom.text.clear()
                                                image.setImageResource(0)
                                                progressBar.visibility = View.GONE
                                                finish()

                                            }.addOnFailureListener {
                                                progressBar.visibility = View.GONE
                                                Toast.makeText(this, resources.getString(R.string.failed), Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    })

                                ?.addOnFailureListener(OnFailureListener { e ->
                                    print(e.message)
                                })
                        }

                    }

                    }else{
                    progressBar.visibility = View.GONE
                    etCpassword.error = resources.getString(R.string.invalid_password)
                    Toast.makeText(this, resources.getString(R.string.enter_valid_informations), Toast.LENGTH_SHORT).show()

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

}

