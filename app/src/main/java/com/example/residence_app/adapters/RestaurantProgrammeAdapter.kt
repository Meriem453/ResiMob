package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantProgrammeAdapter(var c:Context): RecyclerView.Adapter<RestaurantProgrammeAdapter.programmeVH>() {
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
        }
    }

    fun getData(){
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("programme restau").document("saturday")
        var d2 : DocumentReference = db.collection("programme restau").document("sunday")
        var d3 : DocumentReference = db.collection("programme restau").document("monday")
        var d4 : DocumentReference = db.collection("programme restau").document("thuesday")
        var d5: DocumentReference = db.collection("programme restau").document("wednesday")
        var d6 : DocumentReference = db.collection("programme restau").document("thursday")
        var d7 : DocumentReference = db.collection("programme restau").document("friday")


        d1.get().addOnCompleteListener() {
            var day = it.result!!.data?.getValue("day").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
            var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
            data.add(RestaurantProgrammeCardData(day,label1,label2,meal1,meal2))
            notifyDataSetChanged()

            d2.get().addOnCompleteListener() {
                var day = it.result!!.data?.getValue("day").toString().trim()
                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                notifyDataSetChanged()
                d3.get().addOnCompleteListener() {
                    var day = it.result!!.data?.getValue("day").toString().trim()
                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                    var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                    var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                    data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                    notifyDataSetChanged()
                    d4.get().addOnCompleteListener() {
                        var day = it.result!!.data?.getValue("day").toString().trim()
                        var label1 = it.result!!.data?.getValue("label1").toString().trim()
                        var label2 = it.result!!.data?.getValue("label2").toString().trim()
                        var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                        var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                        data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                        notifyDataSetChanged()
                        d5.get().addOnCompleteListener() {
                            var day = it.result!!.data?.getValue("day").toString().trim()
                            var label1 = it.result!!.data?.getValue("label1").toString().trim()
                            var label2 = it.result!!.data?.getValue("label2").toString().trim()
                            var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                            var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                            data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                            notifyDataSetChanged()
                            d6.get().addOnCompleteListener() {
                                var day = it.result!!.data?.getValue("day").toString().trim()
                                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                                var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                                data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                                notifyDataSetChanged()
                                d7.get().addOnCompleteListener() {
                                    var day = it.result!!.data?.getValue("day").toString().trim()
                                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                    var meal1 = it.result!!.data?.getValue("meal1").toString().trim()
                                    var meal2 = it.result!!.data?.getValue("meal2").toString().trim()
                                    data.add(RestaurantProgrammeCardData(day, label1, label2, meal1, meal2))
                                    notifyDataSetChanged()
                                }
                            }
                        }
                    }
                            }
            }


            }


    }
}