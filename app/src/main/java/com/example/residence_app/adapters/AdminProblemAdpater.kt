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
import com.example.residence_app.dialogues.DeleteProblemFragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.*
import org.w3c.dom.Text

class AdminProblemAdpater(val c:Context,val fm:FragmentManager): RecyclerView.Adapter<AdminProblemAdpater.adminprVH>(),DeleteProblemInterface {
var arr=ArrayList<AdminProblemData>()
    lateinit var db : FirebaseFirestore
    var position=0
inner class adminprVH(itemView: View): ViewHolder(itemView){
    val img=itemView.findViewById<ShapeableImageView>(R.id.adminproblem_img)
    val problem=itemView.findViewById<TextView>(R.id.adminproblem_title)
    val name=itemView.findViewById<TextView>(R.id.adminproblem_fullname)
    val president=itemView.findViewById<TextView>(R.id.adminproblem_president)
    val details=itemView.findViewById<TextView>(R.id.adminproblem_details)
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
           date.text=arr[position].time
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
    init {
        arr.clear()
        db = FirebaseFirestore.getInstance()
        db.collection("problem")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            arr.add(dc.getDocument().toObject(AdminProblemData::class.java))


                        }
                    }
                    notifyDataSetChanged()
                }
            })
//        arr.add(AdminProblemData("Resto","Meriem","Zemane",null,"Khoufach","paint"))
//
//        notifyDataSetChanged()
        arr.sortedWith(compareBy({it.sort}))
        arr.reverse()
    }

    fun DeleteProblem(problem:AdminProblemData){
        val fid =problem.pid.toString()
        db.collection("problem").document(fid).delete().addOnSuccessListener{
            Toast.makeText(c,"problem deleted",Toast.LENGTH_LONG).show()
            arr.removeAt(position)
            notifyDataSetChanged()

        }.addOnFailureListener { Toast.makeText(c,"Error!",Toast.LENGTH_LONG).show() }




    }

    override fun DeleteProblem() {
        DeleteProblem(arr[position])
    }
}