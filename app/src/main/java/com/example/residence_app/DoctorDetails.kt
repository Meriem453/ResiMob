package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.residence_app.adapters.TimingAdapter
import com.example.residence_app.databinding.ActivityDoctorDetailsBinding

class DoctorDetails : BaseActivity(){
    private lateinit var binding:ActivityDoctorDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_doctor_details)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=resources.getString(R.string.doctor)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.blue_button))
          val swipe=findViewById<SwipeRefreshLayout>(R.id.doctor_swipe)

        val adapter= TimingAdapter(baseContext,this,intent.getBooleanExtra("isAdmin",false))
        adapter.getDoctorData()
        swipe.setOnRefreshListener {
            swipe.setRefreshing(false)
         adapter.getDoctorData()
        }
        binding.doctorRec.adapter=adapter
        binding.doctorRec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)

    }
}