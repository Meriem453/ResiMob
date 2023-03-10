package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.example.residence_app.databinding.ActivityProblemBinding

class Problem : AppCompatActivity() {
    lateinit var binding:ActivityProblemBinding
    var presidents=ArrayList<String>()
    var prblms=ArrayList<String>()
    var details=ArrayList<String>()
    lateinit var prblmsAdapter:ArrayAdapter<String>
    lateinit var detailsAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_problem)
        supportActionBar?.hide()

        presidents.add("pr1")
        presidents.add("pr2")
        presidents.add("pr3")



        prblmsAdapter=ArrayAdapter(
            baseContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            prblms
        )
        detailsAdapter=ArrayAdapter(
            baseContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            details
        )


        binding.preSpinner.adapter= ArrayAdapter(
            baseContext,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
           presidents)

        binding.prblmSpinner.adapter=prblmsAdapter
        binding.detailsSpinner.adapter=detailsAdapter




        binding.preSpinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getPrblmData(presidents[position],prblmsAdapter)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.prblmSpinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getDetailsData(prblms[position],detailsAdapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        binding.detailsSpinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sendData(details[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}


        }}
    private fun getPrblmData(president:String,adapter: ArrayAdapter<String>){
        //get all president prblms from database and fill the prblm array
        //prblm.add(...)



        adapter.notifyDataSetChanged()

    }
     private fun getDetailsData(prblm:String,adapter: ArrayAdapter<String>){
    //get all prblm details and fill the details array
    //details.add(...)



    adapter.notifyDataSetChanged()
}
private fun sendData(selectedDetail:String){
    //send the problem
}

    }


