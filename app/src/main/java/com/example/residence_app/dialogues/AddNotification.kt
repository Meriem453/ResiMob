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
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.R
import com.google.android.material.textfield.TextInputEditText


class AddNotification : AppCompatDialogFragment() {

    lateinit var title: TextInputEditText
    lateinit var picture : TextInputEditText
    lateinit var details:TextInputEditText
    lateinit var imageUri: Uri
    var presidents=""
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.fragment_add_notification,null)
        builder.setView(view)
        title = view?.findViewById<TextInputEditText>(R.id.addNotif_title)!!
        details = view.findViewById<TextInputEditText>(R.id.addNotif_details)
        val president = view.findViewById<AutoCompleteTextView>(R.id.addNotif_president)
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
                val president=presidents
               // TODO("send new notification")
                this.dismiss()

    }}
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
            valid
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