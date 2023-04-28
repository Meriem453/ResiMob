package com.example.residence_app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.example.residence_app.data.TimingCardData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class RestaurantTimingAdapter(var c:Context): RecyclerView.Adapter<RestaurantTimingAdapter.TimingVH>() {
    private var data=ArrayList<TimingCardData>()
    private lateinit var db : FirebaseFirestore

    inner class TimingVH(itemView: View) : ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.restaurant_timing_card_title)
        val label1=itemView.findViewById<TextView>(R.id.restaurant_timing_card_1label)
        val label2=itemView.findViewById<TextView>(R.id.restaurant_timing_card_2label)
        val timing1 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_1timing)
        val timing2 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_2timing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimingVH {
        val itemView=LayoutInflater.from(c).inflate(R.layout.timing_card,parent,false)
        return TimingVH(itemView)

    }

    override fun getItemCount(): Int {
return data.size
    }

    override fun onBindViewHolder(holder: TimingVH, position: Int) {
              with(holder){
                  title.text=data.get(position).title
                  label1.text=data.get(position).label1
                  label2.text=data.get(position).label2
                  timing1.text=data.get(position).timing1
                  timing2.text=data.get(position).timing2
              }
    }

    fun getData(){
        db = FirebaseFirestore.getInstance()
        db.collection("restau timing")
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



        //here you implement the data from firebase and you fill the array list data

    }

}