package com.example.residence_app.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.residence_app.EditUserActivity
import com.example.residence_app.R
import com.example.residence_app.Interfaces.RefreshAdapter
import com.example.residence_app.data.UserInfo
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.auth.User
import java.io.Serializable

class UsersAdapter(var c:Context): RecyclerView.Adapter<UsersAdapter.usersVH>(),Serializable {
    lateinit var db : FirebaseFirestore
      var arr= ArrayList<UserInfo>()
      var search_arr= emptyList<String>()
    inner class usersVH(itemView: View) : ViewHolder(itemView){
        val img=itemView.findViewById<ShapeableImageView>(R.id.u_img)
        val name=itemView.findViewById<TextView>(R.id.u_fullname)
        val email=itemView.findViewById<TextView>(R.id.u_email)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersVH {
       val itemView=LayoutInflater.from(c).inflate(R.layout.user_card,parent,false)
        return usersVH(itemView)
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: usersVH, position: Int) {

        with(holder){

            Glide.with(c).load(arr[position].image).into(img)
            name.text=arr[position].fname+ " " + arr[position].lname
            email.text=arr[position].email
            itemView.setOnClickListener {
               val intent=Intent(c,EditUserActivity::class.java)
                intent.putExtra("current_user",arr[position])

                intent.flags = FLAG_ACTIVITY_NEW_TASK
                c.startActivity(intent)


            }
        }

    }

    fun getUsersData(){
         arr.clear()
        db = FirebaseFirestore.getInstance()
        db.collection("user")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            val user = dc.getDocument().toObject(UserInfo::class.java)
                            arr.add(user)
                            search_arr=search_arr + "${user.fname} ${user.lname}"


                        }

                    }
                  //  refresh.refresh()
                    notifyDataSetChanged()
                }
            })





    }
companion object{
    fun Refresh(adapter:UsersAdapter)=adapter.getUsersData()
}

}