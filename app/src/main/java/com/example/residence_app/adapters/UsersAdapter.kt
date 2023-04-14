package com.example.residence_app.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.EditUserActivity
import com.example.residence_app.R
import com.example.residence_app.Users
import com.example.residence_app.data.UserInfo

class UsersAdapter(var c:Context): RecyclerView.Adapter<UsersAdapter.usersVH>() {
      var arr= ArrayList<UserInfo>()
      var search_arr= emptyList<String>()
    inner class usersVH(itemView: View) : ViewHolder(itemView){
        val img=itemView.findViewById<ImageView>(R.id.u_img)
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
            //img.setImageURI(arr[position].Image)
            name.text=arr[position].FirstName+ " " + arr[position].LastName
            email.text=arr[position].Email
            itemView.setOnClickListener {
               val intent=Intent(c,EditUserActivity::class.java)
                intent.putExtra("current_user",arr[position])
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                c.startActivity(intent)

            }
        }

    }

    fun getUsersData(){
        arr.add(UserInfo("Zemane","meriem","m_zemane@estin.dz","123456",null,false))



        notifyDataSetChanged()

        for (i in arr){
            search_arr=search_arr + "${i.FirstName} ${i.LastName}"
        }
    }
}