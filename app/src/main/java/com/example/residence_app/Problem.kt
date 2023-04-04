package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import com.example.residence_app.data.ProblemData
import com.example.residence_app.databinding.ActivityProblemBinding

class Problem : AppCompatActivity() {
    lateinit var binding:ActivityProblemBinding
    lateinit var presidents:Array<String>
    lateinit var pre_spinner:AutoCompleteTextView
    lateinit var detail_spinner:AutoCompleteTextView
    lateinit var prblm_spinner:AutoCompleteTextView
    var problems=ArrayList<String>()
    var details=ArrayList<String>()
    var selected_president=""
    var selected_prblm=""
    var selected_detail=""
    lateinit var prblmsAdapter:ArrayAdapter<String>
    lateinit var detailsAdapter:ArrayAdapter<String>
    lateinit var progress_bar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_problem)
        supportActionBar?.hide()
         progress_bar=findViewById(R.id.prblm_progressBar)
         pre_spinner=findViewById<AutoCompleteTextView>(R.id.pre_spinner)
         detail_spinner=findViewById<AutoCompleteTextView>(R.id.details_spinner)
         prblm_spinner=findViewById<AutoCompleteTextView>(R.id.prblm_spinner)
        val send=findViewById<Button>(R.id.prblm_send)

        presidents=resources.getStringArray(R.array.presidents)
        problems.add(resources.getString(R.string.you_have_to_select_a_president))
        details.add(resources.getString(R.string.you_have_to_select_a_problem))

        prblmsAdapter=ArrayAdapter(
            baseContext,
            R.layout.dropdown_item,
            problems
        )
        detailsAdapter=ArrayAdapter(
            baseContext,
            R.layout.dropdown_item,
            details
        )


        pre_spinner.setAdapter(ArrayAdapter(
            baseContext,
            R.layout.dropdown_item,
            presidents))

        prblm_spinner.setAdapter(prblmsAdapter)
        detail_spinner.setAdapter(detailsAdapter)

         pre_spinner.setOnItemClickListener(
    AdapterView.OnItemClickListener { parent, view, position, id ->
        if (parent != null) {
            selected_president= parent.getItemAtPosition(position).toString()
        }
        selected_prblm=""
        selected_detail=""
        problems.clear()
        details.clear()
        problems.add(resources.getString(R.string.you_have_to_select_a_president))
        details.add(resources.getString(R.string.you_have_to_select_a_problem))
        prblmsAdapter.notifyDataSetChanged()
        detailsAdapter.notifyDataSetChanged()

        getPrblmData(prblmsAdapter)
    }
)

          prblm_spinner.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
         selected_prblm=prblm_spinner.text.toString()
              selected_detail=""
              details.clear()
              details.add(resources.getString(R.string.you_have_to_select_a_problem))
              detailsAdapter.notifyDataSetChanged()
         getDetailsData(detailsAdapter)
     })

          detail_spinner.setOnItemClickListener { parent, view, position, id ->
               selected_detail=detail_spinner.text.toString()
           }
send.setOnClickListener {
    if(Check()){
        //TODO("send problems to firebase :)")
        ProblemData(selected_president,selected_prblm,selected_detail)
        progress_bar.visibility=View.VISIBLE
        Toast.makeText(baseContext,"Problem sent",Toast.LENGTH_SHORT).show()
    }
}
    }

    private fun getPrblmData(adapter: ArrayAdapter<String>){
problems.clear()
        when(selected_president){
            presidents[0] ->
            {
              val arr = resources.getStringArray(R.array.pre1_problems)
                for (i in 0..arr.size-1) {
                    problems.add(arr[i])
                }
            }
            presidents[1] ->
            {
                val arr = resources.getStringArray(R.array.pre2_problems)
                for (i in 0..arr.size-1) {
                    problems.add(arr[i])
                }            }
            presidents[2] ->
            {
                val arr = resources.getStringArray(R.array.pre3_problems)
                for (i in 0..arr.size-1) {
                    problems.add(arr[i])
                }            }
            presidents[3] ->
            {
                val arr = resources.getStringArray(R.array.pre4_problems)
                for (i in 0..arr.size-1) {
                    problems.add(arr[i])
                }}
        }

        adapter.notifyDataSetChanged()

    }
     private fun getDetailsData(adapter: ArrayAdapter<String>){
         details.clear()
when(selected_president){
    presidents[0]->{
        when (selected_prblm){
            problems[0]-> {
                val arr = resources.getStringArray(R.array.sport_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[1]-> {
                val arr = resources.getStringArray(R.array.culture_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[2]-> {
                val arr = resources.getStringArray(R.array.health_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }

        }
    }
    presidents[1]->{
        when (selected_prblm){
            problems[0]-> {
                val arr = resources.getStringArray(R.array.timings_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[1]-> {
                val arr = resources.getStringArray(R.array.food_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }


        }
    }
    presidents[2]->{
        when (selected_prblm){
            problems[0]-> {
                val arr = resources.getStringArray(R.array.elec_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[1]-> {
                val arr = resources.getStringArray(R.array.plumbing_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[2]-> {
                val arr = resources.getStringArray(R.array.diy_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }

        }
    }
    presidents[3]->{
        when (selected_prblm){
            problems[0]-> {
                val arr = resources.getStringArray(R.array.internal_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }
            problems[1]-> {
                val arr = resources.getStringArray(R.array.external_details)
                for (i in 0..arr.size-1) {
                    details.add(arr[i])
                }
            }


        }
    }
}
    adapter.notifyDataSetChanged()
}
    private fun Check(): Boolean {
if (selected_president==""){
    pre_spinner.error=resources.getString(R.string.please_select_a_president)
    return false
}else if (selected_prblm==""){
    prblm_spinner.error=resources.getString(R.string.please_select_a_problem)
    return false
}else if (selected_detail==""){
    detail_spinner.error=resources.getString(R.string.please_select_a_detail)
    return false
}else return true

    }

    }


