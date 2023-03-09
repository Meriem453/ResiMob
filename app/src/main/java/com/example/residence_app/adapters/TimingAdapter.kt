package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.example.residence_app.data.TimingCardData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class TimingAdapter(var c:Context): RecyclerView.Adapter<TimingAdapter.TimingVH>() {
    private var data=ArrayList<TimingCardData>()
    private lateinit var db : FirebaseFirestore
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
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("football").document("saturday")
        var d2 : DocumentReference = db.collection("football").document("sunday")
        var d3 : DocumentReference = db.collection("football").document("monday")
        var d4 : DocumentReference = db.collection("football").document("thuesday")
        var d5 : DocumentReference = db.collection("football").document("wednesday")
        var d6 : DocumentReference = db.collection("football").document("thursday")
        var d7 : DocumentReference = db.collection("football").document("friday")


        d1.get().addOnCompleteListener(){
            var day = it.result!!.data?.getValue("title").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var time1 = it.result!!.data?.getValue("time1").toString().trim()
            var time2 = it.result!!.data?.getValue("time2").toString().trim()
            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
            notifyDataSetChanged()
            d2.get().addOnCompleteListener(){
                var day = it.result!!.data?.getValue("title").toString().trim()
                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                var time1 = it.result!!.data?.getValue("time1").toString().trim()
                var time2 = it.result!!.data?.getValue("time2").toString().trim()
                data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                notifyDataSetChanged()
                d3.get().addOnCompleteListener(){
                    var day = it.result!!.data?.getValue("title").toString().trim()
                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                    var time1 = it.result!!.data?.getValue("time1").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                    notifyDataSetChanged()
                    d4.get().addOnCompleteListener(){
                        var day = it.result!!.data?.getValue("title").toString().trim()
                        var label1 = it.result!!.data?.getValue("label1").toString().trim()
                        var label2 = it.result!!.data?.getValue("label2").toString().trim()
                        var time1 = it.result!!.data?.getValue("time1").toString().trim()
                        var time2 = it.result!!.data?.getValue("time2").toString().trim()
                        data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                        notifyDataSetChanged()
                        d5.get().addOnCompleteListener(){
                            var day = it.result!!.data?.getValue("title").toString().trim()
                            var label1 = it.result!!.data?.getValue("label1").toString().trim()
                            var label2 = it.result!!.data?.getValue("label2").toString().trim()
                            var time1 = it.result!!.data?.getValue("time1").toString().trim()
                            var time2 = it.result!!.data?.getValue("time2").toString().trim()
                            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                            notifyDataSetChanged()
                            d6.get().addOnCompleteListener(){
                                var day = it.result!!.data?.getValue("title").toString().trim()
                                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                var time1 = it.result!!.data?.getValue("time1").toString().trim()
                                var time2 = it.result!!.data?.getValue("time2").toString().trim()
                                data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                                notifyDataSetChanged()
                                d7.get().addOnCompleteListener(){
                                    var day = it.result!!.data?.getValue("title").toString().trim()
                                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                    var time1 = it.result!!.data?.getValue("time1").toString().trim()
                                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                                    data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                                    notifyDataSetChanged()}}}}}}}
    }
    fun getFootballInternationaltimingData(){
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("football inter").document("saturday")
        var d2 : DocumentReference = db.collection("football inter").document("sunday")
        var d3 : DocumentReference = db.collection("football inter").document("monday")
        var d4 : DocumentReference = db.collection("football inter").document("thuesday")
        var d5 : DocumentReference = db.collection("football inter").document("wednesday")
        var d6 : DocumentReference = db.collection("football inter").document("thursday")
        var d7 : DocumentReference = db.collection("football inter").document("friday")


        d1.get().addOnCompleteListener(){
            var day = it.result!!.data?.getValue("title").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var time1 = it.result!!.data?.getValue("time1").toString().trim()
            var time2 = it.result!!.data?.getValue("time2").toString().trim()
            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
            notifyDataSetChanged()
            d2.get().addOnCompleteListener(){
                var day = it.result!!.data?.getValue("title").toString().trim()
                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                var time1 = it.result!!.data?.getValue("time1").toString().trim()
                var time2 = it.result!!.data?.getValue("time2").toString().trim()
                data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                notifyDataSetChanged()
                d3.get().addOnCompleteListener(){
                    var day = it.result!!.data?.getValue("title").toString().trim()
                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                    var time1 = it.result!!.data?.getValue("time1").toString().trim()
                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                    data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                    notifyDataSetChanged()
                    d4.get().addOnCompleteListener(){
                        var day = it.result!!.data?.getValue("title").toString().trim()
                        var label1 = it.result!!.data?.getValue("label1").toString().trim()
                        var label2 = it.result!!.data?.getValue("label2").toString().trim()
                        var time1 = it.result!!.data?.getValue("time1").toString().trim()
                        var time2 = it.result!!.data?.getValue("time2").toString().trim()
                        data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                        notifyDataSetChanged()
                        d5.get().addOnCompleteListener(){
                            var day = it.result!!.data?.getValue("title").toString().trim()
                            var label1 = it.result!!.data?.getValue("label1").toString().trim()
                            var label2 = it.result!!.data?.getValue("label2").toString().trim()
                            var time1 = it.result!!.data?.getValue("time1").toString().trim()
                            var time2 = it.result!!.data?.getValue("time2").toString().trim()
                            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                            notifyDataSetChanged()
                            d6.get().addOnCompleteListener(){
                                var day = it.result!!.data?.getValue("title").toString().trim()
                                var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                var time1 = it.result!!.data?.getValue("time1").toString().trim()
                                var time2 = it.result!!.data?.getValue("time2").toString().trim()
                                data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                                notifyDataSetChanged()
                                d7.get().addOnCompleteListener(){
                                    var day = it.result!!.data?.getValue("title").toString().trim()
                                    var label1 = it.result!!.data?.getValue("label1").toString().trim()
                                    var label2 = it.result!!.data?.getValue("label2").toString().trim()
                                    var time1 = it.result!!.data?.getValue("time1").toString().trim()
                                    var time2 = it.result!!.data?.getValue("time2").toString().trim()
                                    data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
                                    notifyDataSetChanged()}}}}}}}
    }
}


