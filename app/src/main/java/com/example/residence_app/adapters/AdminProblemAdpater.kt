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
import com.example.residence_app.Interfaces.DeleteProblemInterface
import com.example.residence_app.R
import com.example.residence_app.data.AdminFeedbackData
import com.example.residence_app.data.AdminProblemData
import com.example.residence_app.data.NotificationData
import com.example.residence_app.data.ProblemData
import com.example.residence_app.dialogues.DeleteProblemFragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.*
import org.w3c.dom.Text

class AdminProblemAdpater(val c:Context,val fm:FragmentManager): RecyclerView.Adapter<AdminProblemAdpater.adminprVH>(),DeleteProblemInterface {
var arr=ArrayList<AdminProblemData>()

    private lateinit var database: DatabaseReference
    var position=0
inner class adminprVH(itemView: View): ViewHolder(itemView){
    val img=itemView.findViewById<ShapeableImageView>(R.id.adminproblem_img)
    val problem=itemView.findViewById<TextView>(R.id.adminproblem_title)
    val name=itemView.findViewById<TextView>(R.id.adminproblem_fullname)
    val president=itemView.findViewById<TextView>(R.id.adminproblem_president)
    val details=itemView.findViewById<TextView>(R.id.adminproblem_president)
    val delete=itemView.findViewById<ImageView>(R.id.adminproblem_delete)
    val layout=itemView.findViewById<LinearLayout>(R.id.adminproblem_layout)
    val date=itemView.findViewById<TextView>(R.id.adminproblem_date)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adminprVH {
        val view = LayoutInflater.from(c).inflate(R.layout.admin_problem_card,parent,false)
         val holder=adminprVH(view)
        return holder
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: adminprVH, position: Int) {
       with(holder){
           //img.setImageURI(arr[position].img)
           problem.text=arr[position].problem
           name.text="${arr[position].fname} ${arr[position].lname}"
           president.text=arr[position].president
           details.text=arr[position].details
           date.text=arr[position].Date
           Glide.with(c).load(arr[position].image).into(img)
           delete.setOnClickListener {
               this@AdminProblemAdpater.position=position
               DeleteProblemFragment(this@AdminProblemAdpater).show(fm,"ffff")

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
    fun getAdminProblemData(){
        arr.clear()
        database = FirebaseDatabase.getInstance().getReference("problems")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (problemSnapshot in snapshot.children){
                        val problem = problemSnapshot.getValue(AdminProblemData::class.java)
                        arr.add(problem!!)
                    }
                    notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Data base error!",error.message.toString())
                return
            }
        })
//        arr.add(AdminProblemData("Resto","Meriem","Zemane",null,"Khoufach","paint"))
//
//        notifyDataSetChanged()
    }
    fun DeleteProblem(problem:AdminProblemData){
        val pid =problem.pid.toString()
        database.child("problems").child(pid).removeValue().addOnSuccessListener{Toast.makeText(c,"problem deleted",Toast.LENGTH_LONG).show()  }.addOnFailureListener { Toast.makeText(c,"Error!",Toast.LENGTH_LONG).show() }



        //code here

        getAdminProblemData()

    }

    override fun DeleteProblem() {
        DeleteProblem(arr[position])
    }
}