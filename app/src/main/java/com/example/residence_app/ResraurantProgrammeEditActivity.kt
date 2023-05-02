package com.example.residence_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.google.android.material.textfield.TextInputEditText

class ResraurantProgrammeEditActivity : BaseActivity() {

    lateinit var title:TextInputEditText
    lateinit var label1:TextInputEditText
    lateinit var label2:TextInputEditText
    lateinit var meal1:TextInputEditText
    lateinit var meal2:TextInputEditText
    lateinit var set:Button
    var tid=""



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
                ))
                setResult(RESULT_OK)
                finish()
            }

        }



    }

    private fun sendNewProgram(newProgram: RestaurantProgrammeCardData) {
     //  TODO("send new program")


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