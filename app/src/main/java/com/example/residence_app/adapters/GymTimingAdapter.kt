package com.example.residence_app.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.TimingEditActivity
import com.example.residence_app.data.AdminFeedbackData
import com.example.residence_app.data.TimingCardData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class GymTimingAdapter(var context : Context ,val activity: Activity,val isAdmin:Boolean) : RecyclerView.Adapter<GymTimingAdapter.ViewHolder>() {
    private var data=ArrayList<TimingCardData>()
    lateinit var db : FirebaseFirestore
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.restaurant_timing_card_title)
        val label1=itemView.findViewById<TextView>(R.id.restaurant_timing_card_1label)
        val label2=itemView.findViewById<TextView>(R.id.restaurant_timing_card_2label)
        val timing1 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_1timing)
        val timing2 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_2timing)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymTimingAdapter.ViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.timing_card,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            title.text=data.get(position).title
            label1.text=data.get(position).label1
            label2.text=data.get(position).label2
            timing1.text=data.get(position).timing1
            timing2.text=data.get(position).timing2
            itemView.isEnabled=isAdmin
            itemView.setOnClickListener {

                val intent= Intent(context, TimingEditActivity::class.java)
                intent.putExtra("place",context.resources.getString(R.string.restaurant))
                intent.putExtra("title",data.get(position).title)
                intent.putExtra("timing1",data.get(position).timing1)
                intent.putExtra("timing2",data.get(position).timing2)
                intent.putExtra("label1",data.get(position).label1)
                intent.putExtra("label2",data.get(position).label2)
                intent.putExtra("tid",data.get(position).tid)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                activity.startActivityForResult(intent,12)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun getGymBoysdata(){
        db = FirebaseFirestore.getInstance()
        db.collection("gym boys")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            data.add(dc.getDocument().toObject(TimingCardData::class.java))


                        }
                    }
                    notifyDataSetChanged()
                }
            })
    }
    fun getGymGirlsdata(){
        db = FirebaseFirestore.getInstance()
        db.collection("gym girls")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            data.add(dc.getDocument().toObject(TimingCardData::class.java))


                        }
                    }
                    notifyDataSetChanged()
                }
            })
    }
}