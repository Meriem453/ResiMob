package com.example.residence_app

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.AdminProblemAdpater

class ProblemAdminAcivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_admin_acivity)

        supportActionBar?.hide()

        val rec=findViewById<RecyclerView>(R.id.admin_problem_rec)
        val adapter= AdminProblemAdpater(baseContext,supportFragmentManager,this)
        rec.adapter=adapter
        rec.layoutManager= LinearLayoutManager(baseContext, RecyclerView.VERTICAL,false)

    }
}