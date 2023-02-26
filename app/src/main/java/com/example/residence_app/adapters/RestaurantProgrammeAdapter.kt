package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.data.RestaurantProgrammeCardData

class RestaurantProgrammeAdapter(var c:Context): RecyclerView.Adapter<RestaurantProgrammeAdapter.programmeVH>() {
    private var data=ArrayList<RestaurantProgrammeCardData>()

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
        //here you implement the data from firebase and you fill the array list data
       data.add(RestaurantProgrammeCardData("Sunday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Monday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Tuesday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Wednesday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Thursday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Friday","Lunch","Dinner","Couscous","Spaghetti"))
        data.add(RestaurantProgrammeCardData("Saturday","Lunch","Dinner","Couscous","Spaghetti"))
        notifyDataSetChanged()
    }
}