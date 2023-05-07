package com.example.residence_app.adapters

import android.content.Context

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.residence_app.FeedbackActivity
import com.example.residence_app.Interfaces.DeleteFeedbackInterface
import com.example.residence_app.R
import com.example.residence_app.data.AdminFeedbackData
import com.example.residence_app.data.ObjectData
import com.example.residence_app.dialogues.DeleteFeedbackFragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.*

class AdminFeedbackAdpater(val c:Context,val fm:FragmentManager): DeleteFeedbackInterface,RecyclerView.Adapter<AdminFeedbackAdpater.adminfdVH>() {
var arr=ArrayList<AdminFeedbackData>()
    lateinit var db : FirebaseFirestore
    var position=0
inner class adminfdVH(itemView: View): ViewHolder(itemView){
    val img=itemView.findViewById<ShapeableImageView>(R.id.adminfeedback_img)
    val title=itemView.findViewById<TextView>(R.id.adminfeedback_title)
    val name=itemView.findViewById<TextView>(R.id.adminfeedback_fullname)
    val president=itemView.findViewById<TextView>(R.id.adminfeedback_president)
    val details=itemView.findViewById<TextView>(R.id.adminfeedback_president)
    val delete=itemView.findViewById<ImageView>(R.id.adminfeedback_delete)
    val layout=itemView.findViewById<LinearLayout>(R.id.adminfeedback_layout)
    val date=itemView.findViewById<TextView>(R.id.adminfeedback_date)
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
           //img.(arr[position].img)
           title.text=arr[position].title
           name.text="${arr[position].fname} ${arr[position].lname}"
           president.text=arr[position].president
           details.text=arr[position].description
           date.text=arr[position].time
           Glide.with(c).load(arr[position].image).into(img)
           delete.setOnClickListener {
               this@AdminFeedbackAdpater.position =position
               DeleteFeedbackFragment(this@AdminFeedbackAdpater).show(fm,null)
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
    init {
        arr.clear()
        db = FirebaseFirestore.getInstance()
        db.collection("feedback")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            arr.add(dc.getDocument().toObject(AdminFeedbackData::class.java))


                        }
                    }
                    notifyDataSetChanged()
                }
            })
        arr.sortedWith(compareBy({it.sort}))
        arr.reverse()

    }

    override fun DeleteFeedback() {
        val fid =arr[position].fid.toString()
        db.collection("feedback").document(fid).delete()
            .addOnSuccessListener{
            Toast.makeText(c,c.resources.getString(R.string.feedback_deleted),Toast.LENGTH_LONG).show()
            arr.removeAt(position)
                notifyDataSetChanged()
            }
            .addOnFailureListener {
            Toast.makeText(c,c.resources.getString(R.string.failed),Toast.LENGTH_LONG).show()
            }

    }
}