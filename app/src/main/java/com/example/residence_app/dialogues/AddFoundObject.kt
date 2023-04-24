package com.example.residence_app.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.R
import com.example.residence_app.data.ObjectData
import com.example.residence_app.data.UserInfo
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class AddFoundObject: AppCompatDialogFragment() {
lateinit var title: TextInputEditText
lateinit var picture : TextInputEditText
lateinit var imageUri:Uri
    var db = Firebase.firestore
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.add_found_object,null)
        builder.setView(view)
        title = view?.findViewById<TextInputEditText>(R.id.addFoundObj_title)!!
        val details = view.findViewById<TextInputEditText>(R.id.addFoundObj_details)
        val place = view.findViewById<TextInputEditText>(R.id.addFoundObj_place)
        picture = view.findViewById<TextInputEditText>(R.id.addFoundObj_picture)!!
        val submit= view.findViewById<Button>(R.id.addFoundObj_submit)

        picture.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,2)
        }

        submit?.setOnClickListener {
            if (Check()){
                val title=title.text.toString()
                val details=details.text.toString()
                val place=place.text.toString()
                val person = resources.getString(R.string.founder)
                var uid = FirebaseAuth.getInstance().currentUser!!.uid
//                uploadImageToFirebase(imageUri!!,uid+"f")
                if (imageUri!! != null) {
                    val fileName = uid +"f.jpg"
                    val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

                    refStorage.putFile(imageUri!!)
                        .addOnSuccessListener(
                            OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                                    val imageUrl = it.toString()
                                    db.collection("user").document(uid).get().addOnCompleteListener{
                                        var fObjectmap = hashMapOf(
                                            "UserFirstName" to it.result!!.data?.getValue("fname").toString().trim(),
                                            "UserLastName" to it.result!!.data?.getValue("lname").toString().trim(),
                                            "Title" to title,
                                            "Details" to details,
                                            "Person" to person,
                                            "UserEmail" to it.result!!.data?.getValue("email").toString().trim(),
                                            "Img" to imageUrl,
                                            "Place" to place,
                                            "oid" to uid
                                        )
                                        db.collection("found objects").document(uid).set(fObjectmap).addOnSuccessListener {
                                            //Toast.makeText(requireContext(),resources.getString(R.string.found_object_submitted),Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                        }.addOnFailureListener {

                                            //Toast.makeText(requireContext(),"Failed!",Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                        }
                                    }
                                }
                            })

                        ?.addOnFailureListener(OnFailureListener { e ->
                            print(e.message)
                        })
                }


                Toast.makeText(requireContext(),resources.getString(R.string.found_object_submitted),Toast.LENGTH_SHORT).show()

                //ObjectData(title,person,imageUri,place,details,"Zemane","Meriem","m_zemane@estin.dz")


                this.dismiss()

            }
        }

        return builder.create()

    }

    private fun Check(): Boolean {
               if(title.text.toString()==""){
                   title.error=resources.getString(R.string.please_enter_a_text)
                   return false
               }else if (picture.text.toString()==""){
                   picture.error=resources.getString(R.string.please_add_an_image)
                   return false
               }else{
                   return true
               }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!=null){
            if (data.data!=null){
                imageUri=data.data!!
                picture.setText(imageUri.toString())
            }
        }
    }

}