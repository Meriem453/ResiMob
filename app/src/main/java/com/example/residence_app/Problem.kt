package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.residence_app.databinding.ActivityProblemBinding

class Problem : AppCompatActivity() {
    lateinit var binding:ActivityProblemBinding
    lateinit var presidents:Array<String>
    var problems=ArrayList<String>()
    var details=ArrayList<String>()
    var selected_president=""
    var selected_prblm=""
    var selected_detail=""
    lateinit var prblmsAdapter:ArrayAdapter<String>
    lateinit var detailsAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        binding=ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_problem)
        supportActionBar?.hide()

        var pre_spinner=findViewById<AutoCompleteTextView>(R.id.pre_spinner)
        val detail_spinner=findViewById<AutoCompleteTextView>(R.id.details_spinner)
        val prblm_spinner=findViewById<AutoCompleteTextView>(R.id.prblm_spinner)

        presidents=resources.getStringArray(R.array.presidents)



        prblmsAdapter=ArrayAdapter(
            baseContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            problems
        )
        detailsAdapter=ArrayAdapter(
            baseContext,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            details
        )


        pre_spinner.setAdapter(ArrayAdapter(
            baseContext,
            R.layout.dropdown_item,
            presidents))

        prblm_spinner.setAdapter(prblmsAdapter)
        detail_spinner.setAdapter(detailsAdapter)




        pre_spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_president= presidents[position]
                getPrblmData(prblmsAdapter)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
            prblm_spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_prblm=problems[position]
                Toast.makeText(baseContext,problems[position].toString(),Toast.LENGTH_SHORT).show()
                getDetailsData(detailsAdapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {} }
        detail_spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_detail=(details[position])

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }}
    private fun getPrblmData(adapter: ArrayAdapter<String>){
        //get all president prblms from database and fill the prblm array
        //use selected_president
        //prblms.add(...)
        problems.clear()
        if(selected_president=="pr1"){
            problems.add("prblm1")
        }else{
            problems.add("prblm2")

        }
        adapter.notifyDataSetChanged()

    }
     private fun getDetailsData(adapter: ArrayAdapter<String>){
    //get all prblm details and fill the details array
         //use selected_prblm

         //details.add(...)
         details.clear()

         if(selected_prblm=="prblm1"){
             details.add("detail1")
         }else{
             details.add("detail2")

         }


    adapter.notifyDataSetChanged()
}


    }


