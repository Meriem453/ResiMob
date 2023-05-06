package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.residence_app.R
import com.example.residence_app.data.SportData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.firebase.firestore.FirebaseFirestore

class SportEditActivity : AppCompatActivity() {
    lateinit var sport:AutoCompleteTextView
    lateinit var kind:AutoCompleteTextView
    lateinit var gender:AutoCompleteTextView
    lateinit var set:Button
    lateinit var time_from:TextInputEditText
    lateinit var time_to:TextInputEditText
    var selected_sport=""
    var selected_kind=""
    var selected_gender=""
    lateinit var tid:String
    lateinit var  db : FirebaseFirestore
    lateinit var d: String



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_edit)
        supportActionBar?.hide()

        sport=findViewById(R.id.sport_spinner)
        kind=findViewById(R.id.kind_spinner)
        gender=findViewById(R.id.gender_spinner)
        time_from=findViewById(R.id.from_spinner)
        time_to=findViewById(R.id.to_spinner)
        set=findViewById(R.id.set_spinner)

        sport.setText(intent.getStringExtra("sport"))
        kind.setText(intent.getStringExtra("kind"))
        gender.setText(intent.getStringExtra("gender"))

        selected_kind=intent.getStringExtra("kind").toString()
        selected_sport=intent.getStringExtra("sport").toString()
        selected_gender=intent.getStringExtra("gender").toString()


        tid= intent.getStringExtra("tid").toString()

        val sports= arrayOf(resources.getString(R.string.football),resources.getString(R.string.volleyball),resources.getString(R.string.basketball))
        val genders= arrayOf(resources.getString(R.string.girls),resources.getString(R.string.boys))
        val  kinds = arrayOf("Nat","Internat","Nat/Internat")

        sport.setAdapter(ArrayAdapter(baseContext,R.layout.dropdown_item,sports))
        kind.setAdapter(ArrayAdapter(baseContext,R.layout.dropdown_item,kinds))
        gender.setAdapter(ArrayAdapter(baseContext,R.layout.dropdown_item,genders))

        sport.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_sport = sports[position]
            }

        }
        kind.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_kind = kinds[position]
            }
        }
        gender.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected_gender = genders[position]
            }
        }



        time_from.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText(resources.getString(R.string.select_time))
                    .build()

            MaterialTimePicker.Builder().setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            picker.show(supportFragmentManager, "tag")
            picker.addOnPositiveButtonClickListener {
                time_from.setText(picker.hour.toString() + ":" + picker.minute.toString())
            }}
            time_to.setOnClickListener {
                val picker =
                    MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText(resources.getString(R.string.select_time))
                        .build()

                MaterialTimePicker.Builder().setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                picker.show(supportFragmentManager, "tag");
                picker.addOnPositiveButtonClickListener {
                    time_to.setText(picker.hour.toString() + ":" + picker.minute.toString())
                }
            }

        set.setOnClickListener {
        if(Check())    {
            sendNewSport(SportData(selected_sport,selected_kind,selected_gender,time_from.text.toString()+"---"+time_to.text.toString())
            ,intent.getIntExtra("day",0),
               intent.getIntExtra("position",0)
            )
            setResult(RESULT_OK)
            finish()
        }}

        }

    private fun sendNewSport(sportData: SportData,day:Int,position:Int) {
        db = FirebaseFirestore.getInstance()
        var n : String
        if(position == 0){
            n = ""
        }else{
            if (position == 1){
                n = "2"
            }else{
                n="3"
            }
        }
        if(day == 0){
            d = "sunday"
        }else{
            if (day == 1){
                d = "monday"
            }else{
                if (day == 2){
                    d = "thuesday"
                }else{
                    if (day == 3){
                        d = "wednesday"
                    }else{
                        if (day == 4){
                            d = "thursday"
                        }else{
                            if (day == 5){
                                d = "friday"
                            }else{
                                if (day == 6){
                                    d = "saturday"
                                }
                            }
                        }
                    }
                }
            }
        }
        val sportMap = mapOf(
            "sport"+n to sportData.sport,
            "gender"+n to sportData.gender,
            "kind"+n to sportData.kind,
            "time" +n to sportData.time
        )
        db.collection("sport room").document(d).update(sportMap).addOnSuccessListener {
            Toast.makeText(baseContext,"Succeed!", Toast.LENGTH_LONG).show()
            finish()
        }.addOnFailureListener { Toast.makeText(baseContext,"Error!", Toast.LENGTH_LONG).show() }

    }

    private fun Check(): Boolean {
var valid=true
        if (sport.text.toString().trim()==""){
            sport.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if (kind.text.toString().trim()==""){
            kind.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if (gender.text.toString().trim()==""){
            gender.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if (time_from.text.toString().trim()=="From"){
            time_from.error=resources.getString(R.string.enter_time)
            valid=false
        }
        if (time_to.text.toString().trim()=="To"){
            time_to.error=resources.getString(R.string.enter_time)
            valid=false
        }
        return valid
    }
}