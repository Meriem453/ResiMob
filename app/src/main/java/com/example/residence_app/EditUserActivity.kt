package com.example.residence_app

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.residence_app.Interfaces.DeleteUserInterface
import com.example.residence_app.adapters.UsersAdapter
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
import kotlin.concurrent.thread

class EditUserActivity : BaseActivity(), DeleteUserInterface {


    lateinit var fname:TextInputEditText
    lateinit var lname:TextInputEditText
    //lateinit var passwrd:TextInputEditText
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
     var position:Int=0
    var changePhoto:Boolean = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
       supportActionBar?.hide()


        fname=findViewById(R.id.edit_first_name)
        lname=findViewById(R.id.edit_last_name)
         //passwrd=findViewById(R.id.edit_password)
         email=findViewById(R.id.edit_email_address)
         room=findViewById(R.id.edit_room)
         img=findViewById(R.id.edit_imageView)
         edit  = findViewById(R.id.editUser_edit)
         delete=findViewById(R.id.editUser_delete)
        cancel=findViewById(R.id.editUser_cancel)
        set=findViewById(R.id.editUser_set)
        vb=findViewById(R.id.vb)

          position=intent.getIntExtra("position",0)



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
            if(Check()) {
                user.fname = fname.text.toString()
                user.lname = lname.text.toString()
                user.email = email.text.toString()
                //user.password = passwrd.text.toString()
                user.room=room.text.toString()
                if (changePhoto) {
                    user.image = uri.toString()
                }
                sendNewUser(user)
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
        //passwrd.isEnabled=value
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
    //passwrd.setText(user.password)
    email.setText(user.email)
     room.setText(user.room)
    image= user.image.toString()
    //uri=Uri.parse(user.image)
    Glide.with(baseContext).load(user.image).into(img)

}


    }
fun Check():Boolean{
    var valid = true

    if(fname.text.toString().trim()==""){
        fname.error=resources.getString(R.string.please_enter_a_text)
        valid=false
    }
    if(lname.text.toString().trim()==""){
        lname.error=resources.getString(R.string.please_enter_a_text)
        valid=false
    }
    if(email.text.toString().trim()==""){
        email.error=resources.getString(R.string.please_enter_a_text)
        valid=false
    }
    /*if(passwrd.text.toString().trim()==""){
        passwrd.error=resources.getString(R.string.please_enter_a_text)
        valid=false
    }*/
    if(room.text.toString().trim()==""){
        room.error=resources.getString(R.string.please_enter_a_text)
        valid=false
    }
    return valid

}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            img.setImageURI(data.data)
            uri= data.data!!
            changePhoto=true
        }
    }




    private fun sendNewUser(currentUser: UserInfo) {
        var uid = currentUser.uid

        if (changePhoto) {
            val fileName = currentUser.uid +".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
            refStorage.putFile(uri!!)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                            db = FirebaseFirestore.getInstance()
                            var newUserMap = mapOf(
                                "uid" to uid,
                                "fname" to currentUser.fname,
                                "lname" to currentUser.lname,
                                "email" to currentUser.email,
                                "password" to currentUser.password,
                                "image" to imageUrl,
                                "isadmin" to false,
                                "room" to currentUser.room,
                            )
                            db.collection("user").document(uid.toString()).update(newUserMap).addOnSuccessListener {
                                Toast.makeText(baseContext,"User edited!",Toast.LENGTH_LONG).show()
                                finish()
                            }.addOnFailureListener {
                                Toast.makeText(baseContext,"Error!",Toast.LENGTH_LONG).show() }
                        }})
        }else{
        db = FirebaseFirestore.getInstance()
        var newUserMap = mapOf(
            "uid" to uid,
            "fname" to currentUser.fname,
            "lname" to currentUser.lname,
            "email" to currentUser.email,
            "password" to currentUser.password,
            "image" to currentUser.image,
            "isadmin" to false,
            "room" to currentUser.room,
        )
        db.collection("user").document(uid.toString()).update(newUserMap).addOnSuccessListener {
            Toast.makeText(baseContext,"User edited!",Toast.LENGTH_LONG).show()
            finish()

        }.addOnFailureListener {
            Toast.makeText(baseContext,"Error!",Toast.LENGTH_LONG).show() }






    }}

    @SuppressLint("SuspiciousIndentation")
    override fun deleteUser() {

        val uid = user.uid.toString()

                db = FirebaseFirestore.getInstance()
                ds = FirebaseStorage.getInstance()
            val adminid = FirebaseAuth.getInstance().currentUser!!.uid


                db.collection("user").document(uid).get().addOnCompleteListener{
                    val mail = it.result!!.data?.getValue("email").toString().trim()
                    val pass = it.result!!.data?.getValue("password").toString().trim()
                db.collection("user").document(uid).delete().addOnCompleteListener {
                    ds.reference.child("images/$uid.jpg").delete()
                    ds.reference.child("images/$uid"+"f.jpg").delete()
                        ds.reference.child("images/$uid"+"l.jpg")
                            db.collection("found objects").document(uid).delete()
                    db.collection("lost objects").document(uid).delete()
                                db.collection("feedback").document(uid).delete()
                    db.collection("problem").document(uid).delete()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mail,pass).addOnCompleteListener {
                        val user = Firebase.auth.currentUser!!

                        user.delete()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "User account deleted.")
                                }
                            }
                        db.collection("user").document(adminid).get().addOnCompleteListener{
                            val adminmail = it.result!!.data?.getValue("email").toString().trim()
                            val adminpass = it.result!!.data?.getValue("password").toString().trim()

                        Toast.makeText(baseContext,resources.getString(R.string.user_deleted),Toast.LENGTH_LONG).show()
                        finish()
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(adminmail,adminpass)
                    }



                }.addOnFailureListener { Toast.makeText(baseContext,"Error!",Toast.LENGTH_LONG).show() }}}

         //UsersAdapter.Refresh(UsersAdapter(baseContext,null))
                  UsersAdapter.arr.remove(user)


    }

}