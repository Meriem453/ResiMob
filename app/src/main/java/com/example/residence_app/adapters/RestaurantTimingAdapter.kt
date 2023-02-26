package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.ResteurantTimingCardData

class RestaurantTimingAdapter(var c:Context): RecyclerView.Adapter<RestaurantTimingAdapter.TimingVH>() {
    private var data=ArrayList<ResteurantTimingCardData>()

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
        //here you implement the data from firebase and you fill the array list data
        data.add(ResteurantTimingCardData("Breakfast","Days of the week","Weekend","06:45---07:45","08:00---08.30"))
        data.add(ResteurantTimingCardData("Lunch","1st Chain","2nd Chain","12:00---13:00","12:15---13.30"))
        data.add(ResteurantTimingCardData("Dinner","1st Chain","2nd Chain","18:30---19:30","18:45---19.15"))
        notifyDataSetChanged()
    }

}