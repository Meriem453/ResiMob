package com.example.residence_app

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.residence_app.data.UserInfo
import com.example.residence_app.dialogues.DeleteUserFragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText

class EditUserActivity : AppCompatActivity(),DeleteUserInterface {


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
    var uri: Uri? =null



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
       supportActionBar?.hide()


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
            DeleteUserFragment(this).show(supportFragmentManager,"ggg")
        }
        cancel.setOnClickListener{
            setEnabled(false)
        }
        set.setOnClickListener {
            if(Check()){
            val currentUser=user
            user.FirstName=fname.text.toString()
            user.LastName=lname.text.toString()
            user.Email=email.text.toString()
            user.Password=passwrd.text.toString()
            user.Image=uri
            user.isAdmin=false
            sendNewUser(currentUser,user)
            setEnabled(false)}
        }
        img.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type ="image/*"
            startActivityForResult(intent,5)
        }
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
fun Check():Boolean{
    var valid = true
    if(uri==null){
        Toast.makeText(baseContext,"Please select profile image",Toast.LENGTH_LONG).show()
        valid=false

    }

    if(fname.text.toString().trim()==""){
        fname.error="Enter valid information"
        valid=false
    }
    if(lname.text.toString().trim()==""){
        lname.error="Enter valid information"
        valid=false
    }
    if(email.text.toString().trim()==""){
        email.error="Enter valid information"
        valid=false
    }
    if(passwrd.text.toString().trim()==""){
        passwrd.error="Enter valid information"
        valid=false
    }
    if(room.text.toString().trim()==""){
        room.error="Enter valid information"
        valid=false
    }
    return valid

}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            img.setImageURI(data.data)
            uri= data.data!!
        }
    }




    private fun sendNewUser(currentUser: UserInfo, newUser: UserInfo) {
        //TODO("edit user information")

    Toast.makeText(baseContext,"User edited",Toast.LENGTH_LONG).show()



    }

    override fun deleteUser() {
        //TODO("delete current user")
        Toast.makeText(baseContext,"User deleted",Toast.LENGTH_LONG).show()

        startActivity(Intent(baseContext,Users::class.java))
    }
}