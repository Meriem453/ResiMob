package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.SportData

class SportSportsAdapter (var c :Context): RecyclerView.Adapter<SportSportsAdapter.SportsVH>() {
    var arr=ArrayList<SportData>()
    inner class SportsVH(itemView: View) : ViewHolder(itemView){
        val sport=itemView.findViewById<TextView>(R.id.sport_details_sport)
        val kind=itemView.findViewById<TextView>(R.id.sport_details_kind)
        val gender=itemView.findViewById<TextView>(R.id.sport_details_gender)
        val time=itemView.findViewById<TextView>(R.id.sport_details_time)
        val icon=itemView.findViewById<ImageView>(R.id.sport_details_icon)

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
          when (arr[position].sport){
              c.resources.getString(R.string.football) -> {
                  icon.setImageDrawable(c.resources.getDrawable(R.drawable.icon_foot))
              }
              c.resources.getString(R.string.volleyball) -> {
                  icon.setImageDrawable(c.getDrawable(R.drawable.icon_volley))
              }
              c.resources.getString(R.string.basketball) -> {
                  icon.setImageDrawable(c.getDrawable(R.drawable.icon_basket))

              }
          }

      }
    }
    public fun getData(dayPosition:Int){
        arr.clear()
        when(dayPosition){
            0 -> {
                //sun
                arr.add(SportData("sun","national","Girls","10:00--12:00"))

            }
            1 -> {
               //mon

                arr.add(SportData("mon","national","Boys","17:00--18:30"))



            }
            2 -> {
                //tusday
                arr.add(SportData("tusday","national","Boys","17:00--18:30"))

            }
            3 -> {
                //wednesday
                arr.add(SportData("wednesday","national","Boys","17:00--18:30"))
            }
            4 -> {
                //thursday
                arr.add(SportData("thursday","national","Boys","17:00--18:30"))
            }
            5 -> {
              //friday
                arr.add(SportData("friday","national","Boys","17:00--18:30"))
            }
            6 -> {
                  //saturday
                arr.add(SportData("saturday","national","Boys","17:00--18:30"))
            }

        }

        notifyDataSetChanged()

    }
}