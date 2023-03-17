package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.SportData

class SportSportsAdapter (var c :Context,var dayPosition:Int): RecyclerView.Adapter<SportSportsAdapter.SportsVH>() {
    var arr=ArrayList<SportData>()
    inner class SportsVH(itemView: View) : ViewHolder(itemView){
        val sport=itemView.findViewById<TextView>(R.id.sport_details_sport)
        val kind=itemView.findViewById<TextView>(R.id.sport_details_kind)
        val gender=itemView.findViewById<TextView>(R.id.sport_details_gender)
        val time=itemView.findViewById<TextView>(R.id.sport_details_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsVH {
      val itemView = LayoutInflater.from(c).inflate(R.layout.sport_card,parent,false)
        return SportsVH(itemView)
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: SportsVH, position: Int) {
      with(holder){
          sport.text=arr[position].sport
          kind.text=arr[position].kind
          gender.text=arr[position].gender
          time.text =arr[position].time

      }
    }
    public fun getData(){
        arr.clear()
        when(dayPosition){
            0 -> {
                //saturday
                arr.add(SportData("Football","national","Girls","10:00--12:00"))
                arr.add(SportData("Volleyball","national","Boys","17:00--18:30"))
                arr.add(SportData("Football","national","Boys","19:00--10:00"))
            }
            1 -> {
               //sunday
                arr.add(SportData("Football","national","Boys","19:00--10:00"))
                arr.add(SportData("Volleyball","national","Boys","17:00--18:30"))
                arr.add(SportData("Football","national","Girls","10:00--12:00"))


            }
            2 -> {
                //monday
            }
            3 -> {
                //tusday
            }
            4 -> {
                //wednesday

            }
            5 -> {
              //thirsday
            }
            6 -> {
                  //friday
            }

        }

        notifyDataSetChanged()

    }
}