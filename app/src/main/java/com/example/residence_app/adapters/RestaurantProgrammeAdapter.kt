package com.example.residence_app.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.ResraurantProgrammeEditActivity
import com.example.residence_app.data.AdminFeedbackData
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class RestaurantProgrammeAdapter(var c:Context,val isAdmin:Boolean,val activity: Activity): RecyclerView.Adapter<RestaurantProgrammeAdapter.programmeVH>() {
    private var data=ArrayList<RestaurantProgrammeCardData>()
    private lateinit var db : FirebaseFirestore

    inner class programmeVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val day = itemView.findViewById<TextView>(R.id.restaurant_programme_card_day)
        val label1=itemView.findViewById<TextView>(R.id.restaurant_programme_card_1label)
        val label2=itemView.findViewById<TextView>(R.id.restaurant_programme_card_2label)
        val meal1 = itemView.findViewById<TextView>(R.id.restaurant_programme_card_1meal)
        val meal2 = itemView.findViewById<TextView>(R.id.restaurant_programme_card_2meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): programmeVH {
        val itemView= LayoutInflater.from(c).inflate(R.layout.restaurant_programme_card,parent,false)
        return programmeVH(itemView)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: programmeVH, position: Int) {
        with(holder){
            day.text=data.get(position).day
            label1.text=data.get(position).label1
            label2.text=data.get(position).label2
            meal1.text=data.get(position).meal1
            meal2.text=data.get(position).meal2


            itemView.isEnabled=isAdmin
            itemView.setOnClickListener {
                val intent = Intent(c,ResraurantProgrammeEditActivity::class.java)
                intent.putExtra("title",data.get(position).day)
                intent.putExtra("label1",data.get(position).label1)
                intent.putExtra("label2",data.get(position).label2)
                intent.putExtra("meal1",data.get(position).meal1)
                intent.putExtra("meal2",data.get(position).meal2)
                intent.putExtra("tid",data.get(position).tid)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                activity.startActivityForResult(intent,13)

            }
        }
    }
init {
        db = FirebaseFirestore.getInstance()
        db.collection("programme restau")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            data.add(dc.getDocument().toObject(RestaurantProgrammeCardData::class.java))
                        }
                    }
                    notifyDataSetChanged()
                }
            })



}}