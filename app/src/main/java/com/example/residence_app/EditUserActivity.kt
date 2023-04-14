package com.example.residence_app

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.residence_app.data.UserInfo
import com.example.residence_app.dialogues.AddFoundObject
import com.example.residence_app.dialogues.DeleteUserDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText

class EditUserActivity : AppCompatActivity() {


    lateinit var fname:TextInputEditText
    lateinit var lname:TextInputEditText
    lateinit var passwrd:TextInputEditText
    lateinit var email:TextInputEditText
    lateinit var room:TextInputEditText
    lateinit var img:ShapeableImageView
    lateinit var edit:Button
    lateinit var delete:ConstraintLayout
    lateinit var cancel:ConstraintLayout
    lateinit var set:Button
    lateinit var vb:LinearLayout
    lateinit var user: UserInfo
    lateinit var uri:Uri



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        fname=findViewById(R.id.edit_first_name)
        lname=findViewById(R.id.edit_last_name)
         passwrd=findViewById(R.id.edit_password)
         email=findViewById(R.id.edit_email_address)
         room=findViewById(R.id.edit_room)
         img=findViewById(R.id.edit_imageView)
         edit  = findViewById(R.id.editUser_edit)
         delete=findViewById(R.id.editUser_delete)
        cancel=findViewById(R.id.editUser_cancel)
        set=findViewById(R.id.editUser_set)
        vb=findViewById(R.id.vb)





       user =intent.getSerializableExtra("current_user") as UserInfo
       setEnabled(false)


        edit.setOnClickListener {
         setEnabled(true)
        }
        delete.setOnClickListener {
            //DeleteUserDialog(baseContext).show(supportFragmentManager,"bla bla")
            //MaterialAlertDialogBuilder(baseContext,R.layout.fragment_delete_user_dialog).show()
            deletUser(user)


        }
        cancel.setOnClickListener{
            setEnabled(false)
        }
        set.setOnClickListener {
            val currentUser=user
            user.FirstName=fname.text.toString()
            user.LastName=lname.text.toString()
            user.Email=email.text.toString()
            user.Password=passwrd.text.toString()
            user.Image=uri
            user.isAdmin=false
            sendNewUser(currentUser,user)
            setEnabled(false)


        }
        img.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,5)
        }
    }

    private fun deletUser(currentUser: UserInfo) {
        //TODO("delete user")

        Toast.makeText(baseContext,"User deleted",Toast.LENGTH_LONG).show()

    }

    private fun sendNewUser(currentUser: UserInfo, newUser: UserInfo) {
        //TODO("edit user information")

        Toast.makeText(baseContext,"User edited",Toast.LENGTH_LONG).show()

    }

    private fun setEnabled(value: Boolean) {
        TransitionManager.beginDelayedTransition( vb, AutoTransition())

        fname.isEnabled=value
        lname.isEnabled=value
        passwrd.isEnabled=value
        email.isEnabled=value
        img.isEnabled=value
        room.isEnabled=value
if(value){
    edit.visibility=View.GONE
    delete.visibility=View.GONE
    cancel.visibility=View.VISIBLE
    set.visibility=View.VISIBLE

}else{

    edit.visibility=View.VISIBLE
    delete.visibility=View.VISIBLE
    cancel.visibility=View.GONE
    set.visibility=View.GONE
    fname.setText(user.FirstName)
    lname.setText(user.LastName)
    passwrd.setText(user.Password)
    email.setText(user.Email)
    // room.setText(user.room)
    img.setImageURI(user.Image)
}


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            img.setImageURI(data.data)
            uri= data.data!!
        }
    }
}