package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.residence_app.databinding.ActivityLoginBinding



class LoginActivity : AppCompatActivity() {
private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)







        binding.btnLogin.setOnClickListener {
            val intent=Intent(this,HomeUserActivity::class.java)
            startActivity(intent)
        }



    }
}