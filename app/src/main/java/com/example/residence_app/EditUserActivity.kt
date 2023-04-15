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
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.net.URL

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
    lateinit var image: String
     lateinit var db : FirebaseFirestore
     lateinit var auth : FirebaseAuth
     lateinit var ds : FirebaseStorage



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
            user.fname=fname.text.toString()
            user.lname=lname.text.toString()
            user.email=email.text.toString()
            user.password=passwrd.text.toString()
            user.image=""
            user.isadmin=false
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
    fname.setText(user.fname)
    lname.setText(user.lname)
    passwrd.setText(user.password)
    email.setText(user.email)
     room.setText(user.room)
    img.setImageURI(uri)
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
        var uid = currentUser.uid

        if (uri!! != null) {
            val fileName = currentUser.uid +".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

            refStorage.putFile(uri!!)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                            db = FirebaseFirestore.getInstance()
                            var newUserMap = mapOf(
                                "uid" to newUser.uid,
                                "fname" to newUser.fname,
                                "lname" to newUser.lname,
                                "email" to newUser.email,
                                "password" to newUser.password,
                                "image" to imageUrl,
                                "isadmin" to false,
                                "room" to newUser.room,
                            )
                            db.collection("user").document(uid.toString()).update(newUserMap).addOnSuccessListener { Toast.makeText(baseContext,"User edited!",Toast.LENGTH_LONG).show() }.addOnFailureListener { Toast.makeText(baseContext,"Error!",Toast.LENGTH_LONG).show() }
                        }})
        }





    }

    override fun deleteUser() {
        val uid = user.uid.toString()
        db = FirebaseFirestore.getInstance()
        ds = FirebaseStorage.getInstance()
        db.collection("user").document(uid).delete().addOnSuccessListener { Toast.makeText(baseContext,"User deleted",Toast.LENGTH_LONG).show() }.addOnFailureListener { Toast.makeText(baseContext,"Error!",Toast.LENGTH_LONG).show() }




        startActivity(Intent(baseContext,Users::class.java))
    }

}