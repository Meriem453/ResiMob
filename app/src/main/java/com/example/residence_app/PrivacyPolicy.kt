package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PrivacyPolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        supportActionBar?.hide()
    }
}