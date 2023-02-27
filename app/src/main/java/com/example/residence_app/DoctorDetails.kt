package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.DoctorTimingAdapter
import com.example.residence_app.databinding.ActivityDoctorDetailsBinding

class DoctorDetails : AppCompatActivity() {
    private lateinit var binding:ActivityDoctorDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=resources.getString(R.string.doctor)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.blue_botton))

        val adapter=DoctorTimingAdapter(baseContext)
        adapter.getData()
        binding.doctorRec.adapter=adapter
        binding.doctorRec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)

    }
}