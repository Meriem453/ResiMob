package com.example.residence_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Calendar

class FeedbackActivity : BaseActivity() {
    lateinit var option : AutoCompleteTextView
    var president =""
lateinit var progressBar: ProgressBar
    private val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm:ss")


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        //lateinit var progressBar: ProgressBar
        lateinit var etTitle : EditText
        lateinit var etDescription : EditText
        //progressBar = findViewById(R.id.feedback_progressbar)


        lateinit var send : Button
         var db = Firebase.firestore
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        supportActionBar?.hide()
        option = findViewById(R.id.spinner_id)
        send = findViewById(R.id.sendFeedback)
        etTitle = findViewById(R.id.title_ID)
        etDescription = findViewById(R.id.description_ID)
        progressBar=findViewById(R.id.feedback_progressBar)
        val options = resources.getStringArray(R.array.presidents)




        option.setAdapter(ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options))
        option.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                president = options[position]
            }

        }
        send.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()


            if(title.isEmpty() || president.isEmpty() || description.isEmpty()){
                if(title.isEmpty()){
                    etTitle.error = resources.getString(R.string.please_enter_a_text)
                }
                if(description.isEmpty()){
                    etDescription.error = resources.getString(R.string.please_enter_a_text)
                }
                if(president==""){
                    Toast.makeText(this, resources.getString(R.string.please_select_a_president), Toast.LENGTH_SHORT).show()
                }
                progressBar.visibility = View.GONE
            }else{

                var uid = FirebaseAuth.getInstance().currentUser!!.uid
                db.collection("user").document(uid).get().addOnCompleteListener{
                    val feedbackmap = hashMapOf(
                        "fname" to it.result!!.data?.getValue("fname").toString().trim(),
                        "lname" to it.result!!.data?.getValue("lname").toString().trim(),
                        "title" to title,
                        "president" to president,
                        "description" to description,
                        "image" to it.result!!.data?.getValue("image").toString().trim(),
                        "fid" to it.result!!.data?.getValue("uid").toString().trim(),
                        "time" to sdf.format(Calendar.getInstance().time).toString()
                        )
                    db.collection("feedback").document(uid).set(feedbackmap).addOnSuccessListener {
                        Toast.makeText(this, resources.getString(R.string.added_succesfully), Toast.LENGTH_SHORT).show()
                        etTitle.text.clear()
                        etDescription.text.clear()
                        progressBar.visibility = View.GONE

                    }.addOnFailureListener {

                        Toast.makeText(this, resources.getString(R.string.failed), Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.GONE
                    }

                }


            }
        }
    }
    }

