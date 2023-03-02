package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.TimingCardData

class TimingAdapter(var c:Context): RecyclerView.Adapter<TimingAdapter.TimingVH>() {
    private var data=ArrayList<TimingCardData>()
    inner class TimingVH(itemView: View): ViewHolder(itemView){
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

    fun getDoctorData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("sunday","morning","evening","08:00---11:00","14:00--16:00"))


        notifyDataSetChanged() }
    fun getAdministrationData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("sunday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("monday","morning","evening","08:00---11:00","14:00--16:00"))

        notifyDataSetChanged()
    }

    fun getBathroomGirlsTimingData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("sunday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("monday","morning","evening","08:00---11:00","14:00--16:00"))
        notifyDataSetChanged()
    }

    fun getBathroomBoysTimingData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("sunday","morning","evening","08:00---11:00","14:00--16:00"))
        data.add(TimingCardData("monday","morning","evening","08:00---11:00","14:00--16:00"))
        notifyDataSetChanged()
    }
    fun getFootballNationaltimingData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))

        notifyDataSetChanged()
    }
    fun getFootballInternationaltimingData(){
        data.add(TimingCardData("saturday","morning","evening","08:00---11:00","14:00--16:00"))

notifyDataSetChanged()
    }
}