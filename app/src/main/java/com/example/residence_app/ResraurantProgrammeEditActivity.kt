package com.example.residence_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class ResraurantProgrammeEditActivity : BaseActivity() {

    lateinit var title:TextInputEditText
    lateinit var label1:TextInputEditText
    lateinit var label2:TextInputEditText
    lateinit var meal1:TextInputEditText
    lateinit var meal2:TextInputEditText
    lateinit var set:Button
    var tid=""
    private lateinit var db :FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resraurant_programme_edit)

        supportActionBar?.hide()

        title=findViewById(R.id.admin_edit_program_title)
        label1=findViewById(R.id.admin_edit_program_label1)
        label2=findViewById(R.id.admin_edit_program_label2)
        meal1=findViewById(R.id.admin_edit_program_meal1)
        meal2=findViewById(R.id.admin_edit_program_meal2)
        set=findViewById(R.id.admin_edit_program_set)

        title.setText(intent.getStringExtra("title"))
        label1.setText(intent.getStringExtra("label1"))
        label2.setText(intent.getStringExtra("label2"))
        meal1.setText(intent.getStringExtra("meal1"))
        meal2.setText(intent.getStringExtra("meal2"))
        tid=intent.getStringExtra("tid").toString()

        set.setOnClickListener {
            if(Check()){
                sendNewProgram(RestaurantProgrammeCardData(
                    title.text.toString()
                    ,label1.text.toString()
                    ,label2.text.toString()
                    ,meal1.text.toString()
                    ,meal2.text.toString()
                ,tid
                ))
                setResult(RESULT_OK)
                finish()
            }

        }



    }

    @SuppressLint("SuspiciousIndentation")
    private fun sendNewProgram(newProgram: RestaurantProgrammeCardData) {
        db = FirebaseFirestore.getInstance()

        var newProgramMap = mapOf(

            "day" to newProgram.day,
            "label1" to newProgram.label1,
            "label2" to newProgram.label2,
            "meal1" to newProgram.meal1,
            "meal2" to newProgram.meal2

            )


            db.collection("programme restau").document(newProgram.tid.toString()).update(newProgramMap).addOnSuccessListener {
                setResult(RESULT_OK)
            }.addOnFailureListener { Toast.makeText(baseContext,"Error!", Toast.LENGTH_LONG).show() }




    }

    private fun Check(): Boolean {
        var valid=true
        if(title.text.toString().trim()==""){
            title.error = resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(label1.text.toString().trim()==""){
            label1.error = resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(label2.text.toString().trim()==""){
            label2.error = resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(meal1.text.toString().trim()==""){
            meal1.error = resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(meal2.text.toString().trim()==""){
            meal2.error = resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        return valid

    }
}