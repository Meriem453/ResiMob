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
import com.example.residence_app.data.ResteurantTimingCardData
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import org.w3c.dom.DocumentType

class RestaurantTimingAdapter(var c:Context): RecyclerView.Adapter<RestaurantTimingAdapter.TimingVH>() {
    private var data=ArrayList<ResteurantTimingCardData>()
    private lateinit var db : FirebaseFirestore

    inner class TimingVH(itemView: View) : ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.restaurant_timing_card_title)
        val label1=itemView.findViewById<TextView>(R.id.restaurant_timing_card_1label)
        val label2=itemView.findViewById<TextView>(R.id.restaurant_timing_card_2label)
        val timing1 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_1timing)
        val timing2 = itemView.findViewById<TextView>(R.id.restaurant_timing_card_2timing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimingVH {
        val itemView=LayoutInflater.from(c).inflate(R.layout.restaurant_timing_card,parent,false)
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
        var dr1 :DocumentReference = db.collection("restau timing").document("1breakfast")
        var dr2 :DocumentReference = db.collection("restau timing").document("2lunch")
        var dr3 :DocumentReference = db.collection("restau timing").document("3dinner")

        dr1.get().addOnCompleteListener() {
            var title = it.result!!.data?.getValue("title").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var timing1 = it.result!!.data?.getValue("timing1").toString().trim()
            var timing2 = it.result!!.data?.getValue("timing2").toString().trim()
            data.add(ResteurantTimingCardData(title,label1,label2,timing1,timing2))
            notifyDataSetChanged()

            dr2.get().addOnCompleteListener() {
                var title = it.result!!.data?.getValue("title").toString().trim()
                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                var timing1 = it.result!!.data?.getValue("timing1").toString().trim()
                var timing2 = it.result!!.data?.getValue("timing2").toString().trim()
                data.add(ResteurantTimingCardData(title,label1,label2,timing1,timing2))
                notifyDataSetChanged()

                dr3.get().addOnCompleteListener() {
                    var title = it.result!!.data?.getValue("title").toString().trim()
                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                    var timing1 = it.result!!.data?.getValue("timing1").toString().trim()
                    var timing2 = it.result!!.data?.getValue("timing2").toString().trim()
                    data.add(ResteurantTimingCardData(title,label1,label2,timing1,timing2))
                    notifyDataSetChanged()
                }
            }
        }



        //here you implement the data from firebase and you fill the array list data

    }

}