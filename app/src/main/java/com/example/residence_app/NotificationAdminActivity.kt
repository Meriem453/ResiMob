package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.Interfaces.RefreshAdapter
import com.example.residence_app.adapters.NotificationsAdapter
import com.example.residence_app.dialogues.AddNotification

class NotificationAdminActivity : AppCompatActivity(),RefreshAdapter {
    lateinit var adapter: NotificationsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_admin)
        supportActionBar?.hide()
        val add =findViewById<Button>(R.id.adminnotif_add)
        add.setOnClickListener {
            AddNotification(this).show(supportFragmentManager,"jvhqb")
        }
        val rec=findViewById<RecyclerView>(R.id.adminnotif_rec)
        rec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)
        adapter=NotificationsAdapter(baseContext,true,supportFragmentManager)
        adapter.getNotifications()
        rec.adapter=adapter
    }

    override fun refresh() {
        adapter.getNotifications()
    }
}