package com.example.residence_app.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.R
import com.example.residence_app.data.ObjectData
import com.example.residence_app.data.UserInfo
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddLostObject: AppCompatDialogFragment() {
lateinit var title: TextInputEditText
    var db = Firebase.firestore
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.add_lost_object,null)
        builder.setView(view)
        title = view?.findViewById<TextInputEditText>(R.id.addLostObj_title)!!
        val details = view.findViewById<TextInputEditText>(R.id.addLostObj_details)
        val place = view.findViewById<TextInputEditText>(R.id.addLostObj_place)
        val submit= view.findViewById<Button>(R.id.addLostObj_submit)

        submit?.setOnClickListener {
            if (Check()){
                val title=title.text.toString()
                val details=details.text.toString()
                val place=place.text.toString()
                val person = "Loster"

                //ObjectData(title,person,null,place,details,"Zemane","Meriem","m_zemane@estin.dz")
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
                    )
                    db.collection("lost objects").document(uid).set(lObjectmap).addOnSuccessListener {
                        //Toast.makeText(requireContext(),resources.getString(R.string.found_object_submitted),Toast.LENGTH_SHORT).show()
                        //progressBar.visibility = View.GONE
                    }.addOnFailureListener {

                        //Toast.makeText(requireContext(),"Failed!",Toast.LENGTH_SHORT).show()
                        //progressBar.visibility = View.GONE
                    }
                    }



                Toast.makeText(requireContext(),resources.getString(R.string.lost_object_submitted),Toast.LENGTH_SHORT).show()
                this.dismiss()

            }
        }

        return builder.create()

    }

    private fun Check(): Boolean {
               if(title.text.toString() == ""){
                   title.error=resources.getString(R.string.please_enter_a_text)
                   return false
               }else{
                   return true
               }
    }

}