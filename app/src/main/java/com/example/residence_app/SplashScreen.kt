package com.example.residence_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import com.example.residence_app.dialogues.ChangeLanguage
import kotlinx.coroutines.GlobalScope
import java.util.Locale

class SplashScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("language","en")
        if(language!=null){
            BaseActivity.dLocale = Locale(language)

        }
        Handler(Looper.getMainLooper()).postDelayed({
            val x = this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE).getString("isChecked","xxx")
            if(x != "true"){
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (x == "true"){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 3000)
    }
}