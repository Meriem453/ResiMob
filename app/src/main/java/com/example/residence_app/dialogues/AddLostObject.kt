package com.example.residence_app.dialogues

import android.app.AlertDialog
import android.app.Dialog


import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Calendar



class AddLostObject: AppCompatDialogFragment() {
lateinit var etitle: TextInputEditText
    private val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm:ss")
    var db = Firebase.firestore
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.add_lost_object,null)
        builder.setView(view)
        etitle = view?.findViewById<TextInputEditText>(R.id.addLostObj_title)!!
        val edetails = view.findViewById<TextInputEditText>(R.id.addLostObj_details)
        val eplace = view.findViewById<TextInputEditText>(R.id.addLostObj_place)
        val esubmit= view.findViewById<Button>(R.id.addLostObj_submit)

        esubmit.setOnClickListener {
            if (Check()){
                val title=etitle.text.toString()
                val details=edetails.text.toString()
                val place=eplace.text.toString()
                val person = "Loster"

                var uid = FirebaseAuth.getInstance().currentUser!!.uid

                                    db.collection("user").document(uid).get().addOnCompleteListener{
                                        var lObjectmap = hashMapOf(
                                            "UserFirstName" to it.result!!.data?.getValue("fname").toString().trim(),
                                            "UserLastName" to it.result!!.data?.getValue("lname").toString().trim(),
                                            "Title" to title,
                                            "Details" to details,
                                            "Person" to person,
                                            "UserEmail" to it.result!!.data?.getValue("email").toString().trim(),
                                            "Img" to null,
                                            "Place" to place,
                                            "oid" to uid,
                                            "time" to sdf.format(Calendar.getInstance().time).toString()
                                        )
                                        db.collection("lost objects").document(uid).delete().addOnCompleteListener {
                                            Thread.sleep(1_000)

                                            db.collection("lost objects").document(uid).set(lObjectmap).addOnSuccessListener {
                                            Toast.makeText(requireContext(),resources.getString(R.string.lost_object_submitted),Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                            etitle.text?.clear()
                                            edetails.text?.clear()
                                            eplace.text?.clear()

                                        }.addOnFailureListener {
                                            etitle.text?.clear()
                                            edetails.text?.clear()
                                            eplace.text?.clear()
                                            Toast.makeText(requireContext(),"Failed!",Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                        } }


                                    }

                                }



        }

        return builder.create()

    }

    private fun Check(): Boolean {
               if(etitle.text.toString() == ""){
                   etitle.error=resources.getString(R.string.please_enter_a_text)
                   return false
               }else{
                   return true
               }
    }


}