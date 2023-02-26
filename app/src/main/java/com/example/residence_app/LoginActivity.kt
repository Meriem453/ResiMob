package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.residence_app.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
private lateinit var binding:ActivityLoginBinding
private lateinit var auth: FirebaseAuth
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

                    binding.loginMail.error = "Enter valid email"
                }
                if (email.isEmpty()){
                    binding.loginMail.error = "Enter the email"
                }
                if ( password.isEmpty()){
                    binding.loginPassword.error = "Enter the password"
                }
                progressBar.visibility = View.GONE
            }else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        progressBar.visibility = View.GONE
                        val intent = Intent(this,HomeUserActivity::class.java)
                        startActivity(intent)
                    }else{
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Enter valid details!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }
    //ida l user dakhl men qbl wla nn
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this,HomeUserActivity::class.java)
            startActivity(intent)
        }
    }
}