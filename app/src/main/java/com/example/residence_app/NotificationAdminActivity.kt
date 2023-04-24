package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.residence_app.dialogues.AddNotification

class NotificationAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_admin)
        supportActionBar?.hide()
        val add =findViewById<Button>(R.id.adminnotif_add)
        add.setOnClickListener {
            AddNotification().show(supportFragmentManager,"jvhqb")
        }
    }
}