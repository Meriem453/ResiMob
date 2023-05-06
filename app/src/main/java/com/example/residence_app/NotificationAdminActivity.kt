package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.Interfaces.RefreshAdapter
import com.example.residence_app.adapters.NotificationsAdapter
import com.example.residence_app.data.NotificationData
import com.example.residence_app.dialogues.AddNotification
import com.example.residence_app.dialogues.ShowNotification

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
        var layoutManager :LinearLayoutManager= rec.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        adapter=NotificationsAdapter(baseContext,true,supportFragmentManager)
        adapter.getNotifications()
        adapter.setOnItemClickListener(object : NotificationsAdapter.OnItemClickListener{
            override fun onItemClick(item: NotificationData) {
                var t = item.title
                var d = item.details
                var ti = item.time
                var img = check(item.image.toString())

                ShowNotification(title = t!!, details = d!!, image = img!! ).show(supportFragmentManager,"hhh")
            }

        })
        rec.adapter=adapter
    }
    private fun check(text:String): String {
        if(text == null){
            return ""
        }else{
            return text
        }
    }
    override fun refresh() {
        adapter.getNotifications()
    }
}