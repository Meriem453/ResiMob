package com.example.residence_app.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.residence_app.R
import com.example.residence_app.data.ObjectData
import com.example.residence_app.data.UserInfo
import com.google.android.material.textfield.TextInputEditText

class AddFoundObject: AppCompatDialogFragment() {
lateinit var title: TextInputEditText
lateinit var picture : TextInputEditText
lateinit var imageUri:Uri
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
                //hadhi nta badalha (3amarha bel info te3 el user)
                val userInfo=UserInfo("Zemane","Meriem","m_zemane@estin.dz","123456",imageUri,false)
                TODO("send the found object to firebase :)")
                ObjectData(title,person,imageUri,place,details,userInfo)
                //hna zid el code bach tajouti the found object fel firebase
                //
                //
                //
                Toast.makeText(requireContext(),resources.getString(R.string.found_object_submitted),Toast.LENGTH_SHORT).show()
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