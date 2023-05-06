package com.example.residence_app.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.Interfaces.RefreshAdapter
import com.example.residence_app.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.Calendar


class AddNotification(val refreshAdapter: RefreshAdapter) : AppCompatDialogFragment() {

    lateinit var title: TextInputEditText
    lateinit var picture : TextInputEditText
    lateinit var details:TextInputEditText
    var selected_type=""
    lateinit var type:AutoCompleteTextView

    private val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
    private val sdfid = SimpleDateFormat("yyyyMMddhhmmss")

     var imageUri : Uri? = null
    lateinit var db : FirebaseFirestore
    var presidents=""
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.fragment_add_notification,null)
        builder.setView(view)
        title = view?.findViewById<TextInputEditText>(R.id.addNotif_title)!!
        details = view.findViewById<TextInputEditText>(R.id.addNotif_details)
        val president = view.findViewById<AutoCompleteTextView>(R.id.addNotif_president)
         type=view.findViewById<AutoCompleteTextView>(R.id.addNotif_type)
        picture = view.findViewById<TextInputEditText>(R.id.addNotif_picture)!!
        val submit= view.findViewById<Button>(R.id.addNotif_submit)
        //setup president spinner
        val options = resources.getStringArray(R.array.presidents)
        president.setAdapter(ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options))
        president.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presidents = options[position]
            }

        }

        //setup type spinner
        val types= arrayOf(resources.getString(R.string.time_chnage),resources.getString(R.string.annoucement))
        type.setAdapter(ArrayAdapter<String>(requireContext(),R.layout.dropdown_item,types))
        type.setOnItemClickListener { parent, view, position, id ->
            selected_type=types[position]
        }


        picture.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,2)
        }

        submit?.setOnClickListener {
            if (Check()){
                db = FirebaseFirestore.getInstance()
                val title=title.text.toString()
                val details=details.text.toString()
                val president=presidents



                    val nid = sdfid.format(Calendar.getInstance().time).toString()

                if (imageUri != null) {
                    val fileName = nid +".jpg"
                    val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

                    refStorage.putFile(imageUri!!)
                        .addOnSuccessListener(
                            OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                                    val imageUrl = it.toString()

                                        var notificationMap = hashMapOf(
                                            "title" to title,
                                            "details" to details,
                                            "image" to imageUrl,
                                            "nid" to nid,
                                            "president" to president,
                                            "time" to sdf.format(Calendar.getInstance().time).toString(),
                                            "is time change" to false,
                                        )
                                        db.collection("notifications").document(nid).set(notificationMap).addOnSuccessListener {
                                            //Toast.makeText(requireContext(),"Notification Added successfully",Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                            refreshAdapter.refresh()
                                            this.dismiss()
                                        }.addOnFailureListener {

                                            //Toast.makeText(requireContext(),"Failed!",Toast.LENGTH_SHORT).show()
                                            //progressBar.visibility = View.GONE
                                        }
                                    }

                            })

                        ?.addOnFailureListener(OnFailureListener { e ->
                            print(e.message)
                        })
                }else{
                    var notificationMap = hashMapOf(
                        "title" to title,
                        "details" to details,
                        "image" to null,
                        "nid" to nid,
                        "president" to president,
                        "time" to sdf.format(Calendar.getInstance().time).toString()
                    )
                    db.collection("notifications").document(nid).set(notificationMap).addOnSuccessListener {
                        //Toast.makeText(requireContext(),"Notification Added successfully",Toast.LENGTH_SHORT).show()
                        refreshAdapter.refresh()
                        //progressBar.visibility = View.GONE
                        this.dismiss()
                    }.addOnFailureListener {

                        //Toast.makeText(requireContext(),"Failed!",Toast.LENGTH_SHORT).show()
                        //progressBar.visibility = View.GONE
                    }
                }



            ?.addOnFailureListener(OnFailureListener { e ->
            print(e.message)
        })
                }


                this.dismiss()

    }
        return builder.create()
    }
    private fun Check(): Boolean {
        var valid = true
        if(title.text.toString()==""){
            title.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(details.text.toString().trim()==""){
            details.error=resources.getString(R.string.please_enter_a_text)
            valid=false
        }
        if(type.text.toString().trim()==""){
            details.error=resources.getString(R.string.select_type)
            valid=false
        }
        return valid
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