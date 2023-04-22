package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.TimingAdapter
import com.example.residence_app.databinding.ActivityAdministrationDetailsBinding

class AdministrationDetails : BaseActivity() {
    private lateinit var binding:ActivityAdministrationDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration_details)
        binding=ActivityAdministrationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title=resources.getString(R.string.administration)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.blue_button))

        val adapter= TimingAdapter(baseContext)
        adapter.getAdministrationData()
        binding.adminRec.adapter=adapter
        binding.adminRec.layoutManager=
            LinearLayoutManager(baseContext, RecyclerView.VERTICAL,false)
    }
}