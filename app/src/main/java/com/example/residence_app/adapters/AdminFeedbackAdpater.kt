package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.residence_app.R
import com.example.residence_app.data.AdminFeedbackData
import com.google.android.material.imageview.ShapeableImageView

class AdminFeedbackAdpater(val c:Context): RecyclerView.Adapter<AdminFeedbackAdpater.adminfdVH>() {
var arr=ArrayList<AdminFeedbackData>()
inner class adminfdVH(itemView: View): ViewHolder(itemView){
    val img=itemView.findViewById<ShapeableImageView>(R.id.adminfeedback_img)
    val title=itemView.findViewById<TextView>(R.id.adminfeedback_title)
    val name=itemView.findViewById<TextView>(R.id.adminfeedback_fullname)
    val president=itemView.findViewById<TextView>(R.id.adminfeedback_president)
    val details=itemView.findViewById<TextView>(R.id.adminfeedback_president)
    val delete=itemView.findViewById<ImageView>(R.id.adminfeedback_delete)
    val layout=itemView.findViewById<LinearLayout>(R.id.adminfeedback_layout)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adminfdVH {
        val view = LayoutInflater.from(c).inflate(R.layout.admin_feedback_card,parent,false)
         val holder=adminfdVH(view)
        return holder
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: adminfdVH, position: Int) {
       with(holder){
           img.setImageURI(arr[position].img)
           title.text=arr[position].title
           name.text="${arr[position].first_name} ${arr[position].last_name}"
           president.text=arr[position].president
           details.text=arr[position].details

           delete.setOnClickListener {
               DeleteFeedback(arr[position])
           }

           itemView.setOnClickListener {
               if(layout.visibility== View.GONE){
                   TransitionManager.beginDelayedTransition(holder.itemView as ViewGroup, AutoTransition())
                   holder.layout.visibility = View.VISIBLE
               }else{
                   //TransitionManager.beginDelayedTransition(holder.itemView as ViewGroup, AutoTransition())
                   holder.layout.visibility = View.GONE
               }
           }
       }
    }
    fun getAdminFeedbackData(){
        arr.clear()
        //TODO("get all the feedbacks")
        arr.add(AdminFeedbackData("Resto","Meriem","Zemane",null,"Khoufach","paint"))

        notifyDataSetChanged()
    }
    fun DeleteFeedback(feedback:AdminFeedbackData){
        //TODO("delete this feedback")
        Toast.makeText(c,"feedback deleted",Toast.LENGTH_LONG).show()

        //code here

        getAdminFeedbackData()

    }
}