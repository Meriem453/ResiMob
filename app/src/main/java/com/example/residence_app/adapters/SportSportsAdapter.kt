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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class SportSportsAdapter (var c :Context): RecyclerView.Adapter<SportSportsAdapter.SportsVH>() {
    var arr=ArrayList<SportData>()
    private lateinit var db : FirebaseFirestore
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
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("sport room").document("saturday")
        var d2 : DocumentReference = db.collection("sport room").document("sunday")
        var d3 : DocumentReference = db.collection("sport room").document("monday")
        var d4 : DocumentReference = db.collection("sport room").document("thuesday")
        var d5: DocumentReference = db.collection("sport room").document("wednesday")
        var d6 : DocumentReference = db.collection("sport room").document("thursday")
        var d7 : DocumentReference = db.collection("sport room").document("friday")
        when(dayPosition){




            0 -> {
                //sun
                d2.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                    }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }


            }
            1 -> {
               //mon
                d3.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }

            }
            2 -> {
                //thues
                d4.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }

            }
            3 -> {
                //wedn
                d5.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }
            }
            4 -> {
                //thurs
                d6.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }
            }
            5 -> {
              //friday
                d7.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }
            }
            6 -> {
                  //saturday
                d1.get().addOnCompleteListener() {
                    var sport = it.result!!.data?.getValue("sport").toString().trim()
                    var gender = it.result!!.data?.getValue("gender").toString().trim()
                    var kind = it.result!!.data?.getValue("kind").toString().trim()
                    var time = it.result!!.data?.getValue("time").toString().trim()
                    arr.add(SportData(sport,kind,gender,time))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport2 = it.result!!.data?.getValue("sport2").toString().trim()
                    var gender2 = it.result!!.data?.getValue("gender2").toString().trim()
                    var kind2 = it.result!!.data?.getValue("kind2").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    arr.add(SportData(sport2,kind2,gender2,time2))
                    notifyDataSetChanged()
                }.addOnCompleteListener{
                    var sport3 = it.result!!.data?.getValue("sport3").toString().trim()
                    var gender3 = it.result!!.data?.getValue("gender3").toString().trim()
                    var kind3 = it.result!!.data?.getValue("kind3").toString().trim()
                    var time3 = it.result!!.data?.getValue("time3").toString().trim()
                    arr.add(SportData(sport3,kind3,gender3,time3))
                    notifyDataSetChanged()
                }
            }

        }

        notifyDataSetChanged()

    }
}