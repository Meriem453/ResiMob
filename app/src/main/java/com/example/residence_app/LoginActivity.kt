package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.residence_app.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : BaseActivity() {
private lateinit var binding:ActivityLoginBinding
private lateinit var auth: FirebaseAuth
private lateinit var db :FirebaseFirestore
private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val progressBar = binding.loginProgressBar
        progressBar.bringToFront()
        progressBar.invalidate()
        binding.resImg.invalidate()
        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val  email = binding.loginMail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()
            if(email.isEmpty() || password.isEmpty()|| !email.matches(emailPattern.toRegex())){
                if (!email.matches(emailPattern.toRegex())) {

                    binding.loginMail.error = resources.getString(R.string.enter_valid_email)
                }
                if (email.isEmpty()){
                    binding.loginMail.error = resources.getString(R.string.please_enter_a_text)
                }
                if ( password.isEmpty()){
                    binding.loginPassword.error =  resources.getString(R.string.please_enter_a_text)
                }
                progressBar.visibility = View.GONE
            }else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        progressBar.visibility = View.GONE
                        db = FirebaseFirestore.getInstance()
                          db.collection("user").document(auth.currentUser!!.uid).get().addOnCompleteListener() {
                              val name = it.result!!.data?.getValue("lname").toString().trim()
                              if(name == "Admin"){
                                  Handler(Looper.getMainLooper()).postDelayed({
                                      val intent = Intent(this, HomeAdminActivity::class.java)
                                      startActivity(intent)
                                      finish()
                                  }, 1)

                              }else{
                                  Handler(Looper.getMainLooper()).postDelayed({
                                      val intent = Intent(this, MainActivity::class.java)
                                      startActivity(intent)
                                      finish()
                                  }, 1)

                              }

                          }

                    }else{
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, resources.getString(R.string.enter_valid_informations), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }
    //ida l user dakhl men qbl wla nn
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null) {
            db = FirebaseFirestore.getInstance()
            db.collection("user").document(auth.currentUser!!.uid).get().addOnCompleteListener() {
                val name = it.result!!.data?.getValue("lname").toString().trim()
                if (name == "Admin") {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, HomeAdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 1)

                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 1)
                }
            }
        }}}
