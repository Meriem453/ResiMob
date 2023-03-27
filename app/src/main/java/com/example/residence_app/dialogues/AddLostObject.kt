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

class AddLostObject: AppCompatDialogFragment() {
lateinit var title: TextInputEditText
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
                val person = resources.getString(R.string.founder)
                //hadhi nta badalha
                val userInfo=UserInfo("Zemane","Meriem","m_zemane@estin.dz","123456",null,false)
                //
                ObjectData(title,person,null,place,details,userInfo)
                //hna zid el code bach tajouti the found object fel firebase
                //
                //
                //
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