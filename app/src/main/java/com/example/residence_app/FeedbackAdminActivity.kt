package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.AdminFeedbackAdpater

class FeedbackAdminActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_admin)

        supportActionBar?.hide()

        val rec=findViewById<RecyclerView>(R.id.admin_feedback_rec)
        val adapter= AdminFeedbackAdpater(baseContext,supportFragmentManager)
        adapter.getAdminFeedbackData()
        rec.adapter=adapter
        rec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)
        //add in top
        var layoutManager :LinearLayoutManager= rec.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }
}