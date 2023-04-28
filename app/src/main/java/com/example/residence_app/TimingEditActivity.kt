package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.residence_app.data.TimingCardData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class TimingEditActivity : BaseActivity() {
    lateinit var place:TextView
    lateinit var label1:TextView
    lateinit var label2:TextView
    lateinit var timing1_from:TextInputEditText
    lateinit var timing1_to:TextInputEditText
    lateinit var timing2_from:TextInputEditText
    lateinit var timing2_to:TextInputEditText
    lateinit var title:TextInputEditText
    lateinit var send:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timing_edit)
          supportActionBar?.hide()
        place=findViewById(R.id.admin_edit_place)
        label1=findViewById(R.id.admin_edit_label1)
        label2=findViewById(R.id.admin_edit_label2)
        timing1_from=findViewById(R.id.admin_edit_timing1_from)
        timing1_to=findViewById(R.id.admin_edit_timing1_to)
        timing2_from=findViewById(R.id.admin_edit_timing2_from)
        timing2_to=findViewById(R.id.admin_edit_timing2_to)
        title=findViewById(R.id.admin_edit_title)
        send=findViewById(R.id.admin_edit_send)


        title.setText(intent.getStringExtra("title"))
        place.text=intent.getStringExtra("place")
        label1.text=intent.getStringExtra("label1")
        label2.text=intent.getStringExtra("label2")



        timing1_from.setOnClickListener {
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
                timing1_from.setText(picker.hour.toString() + ":" + picker.minute.toString())
            }
        }
        timing1_to.setOnClickListener {
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
                timing1_to.setText(picker.hour.toString() + ":" + picker.minute.toString())
            }
        }
        timing2_from.setOnClickListener {
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
                timing2_from.setText(picker.hour.toString() + ":" + picker.minute.toString())
            }
        }
        timing2_to.setOnClickListener {
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
                timing2_to.setText(picker.hour.toString() + ":" + picker.minute.toString())
            }
        }

        send.setOnClickListener {
            if(Check()){
                sendNewTime(place.text.toString()
                        ,TimingCardData(title.text.toString()
                        ,label1.text.toString()
                        ,label2 .text.toString()
                        ,timing1_from.text.toString() + "--" + timing1_to.text.toString()
                        ,timing2_from.text.toString() + "--" + timing2_to.text.toString()))
                setResult(RESULT_OK)
                finish()
            }
        }

    }


    private fun sendNewTime(place:String,timingCardData: TimingCardData) {
         //  TODO("send new time")


    }

    private fun Check(): Boolean {
           var valid = true
        if(timing1_from.text.toString().trim()==resources.getString(R.string.from)){
            timing1_from.error = resources.getString(R.string.enter_time)
            valid=false
        }
        if(timing1_to.text.toString().trim()==resources.getString(R.string.to)){
            timing1_to.error = resources.getString(R.string.enter_time)
            valid=false
        }
        if(timing2_from.text.toString().trim()==resources.getString(R.string.from)){
            timing2_from.error = resources.getString(R.string.enter_time)
            valid=false
        }
        if(timing2_to.text.toString().trim()==resources.getString(R.string.to)){
            timing2_to.error = resources.getString(R.string.enter_time)
            valid=false
        }
        if(title.text.toString().trim()==""){
            title.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }

        return valid


    }
}