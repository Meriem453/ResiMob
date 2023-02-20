package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

class LoginActivity : AppCompatActivity() {
    //variables
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var buttonLogin: Button
    //progressbar li yetaficha
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.mail)
        etPassword = findViewById(R.id.passwrd)
        buttonLogin = findViewById(R.id.btn_login)

        buttonLogin.setOnClickListener{}
    }
}