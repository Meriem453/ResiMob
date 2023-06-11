package com.example.residence_app.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
import com.example.residence_app.notification.FcmNotificationsSender
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.*
import org.w3c.dom.Text

class AdminProblemAdpater(val c:Context,val fm:FragmentManager,val act:Activity): RecyclerView.Adapter<AdminProblemAdpater.adminprVH>(),DeleteProblemInterface {
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
    val EtReply=itemView.findViewById<EditText>(R.id.adminproblem_reply)
    val send_reply=itemView.findViewById<TextView>(R.id.adminproblem_send_reply)
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
           val reply = EtReply.text.toString().trim()
           delete.setOnClickListener {
               this@AdminProblemAdpater.position=position
               DeleteProblemFragment(this@AdminProblemAdpater).show(fm,"ffff")

           }
           send_reply.setOnClickListener {
                val uid =   arr[position].pid
                   FirebaseDatabase.getInstance().getReference("tokens").child(uid.toString()).get().addOnCompleteListener {
                       val token = it.result!!.value.toString().trim()
                       val notifSender = FcmNotificationsSender(
                           token, "ResiMob: "+president.text,
                           reply, c, act
                       )
                       notifSender.SendNotifications()
                   }
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
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("user").document(uid).get().addOnCompleteListener {
            val lname = it.result!!.data?.getValue("lname")
            db.collection("problem")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                        if(error != null){

                            Log.e("Data base error!",error.message.toString())
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                if(dc.getDocument().toObject(AdminProblemData::class.java).role == lname ||lname == "Admin"|| lname == "" )
                                    arr.add(dc.getDocument().toObject(AdminProblemData::class.java))


                            }
                        }
                        arr.sortBy { it.sort }
                        arr.reverse()
                        notifyDataSetChanged()
                    }
                })
        }

//        arr.add(AdminProblemData("Resto","Meriem","Zemane",null,"Khoufach","paint"))
//
//        notifyDataSetChanged()


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