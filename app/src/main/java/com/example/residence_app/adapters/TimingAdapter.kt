package com.example.residence_app.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.TimingEditActivity
import com.example.residence_app.data.RestaurantProgrammeCardData
import com.example.residence_app.data.TimingCardData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class TimingAdapter(var c:Context,val activity: Activity,val isAdmin:Boolean): RecyclerView.Adapter<TimingAdapter.TimingVH>() {
    private var data=ArrayList<TimingCardData>()
    var place=""
     lateinit var db : FirebaseFirestore
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
           itemView.isEnabled=isAdmin
            itemView.setOnClickListener {

                val intent= Intent(c, TimingEditActivity::class.java)
                intent.putExtra("place",place)
                intent.putExtra("title",data.get(position).title)
                intent.putExtra("timing1",data.get(position).timing1)
                intent.putExtra("timing2",data.get(position).timing2)
                intent.putExtra("label1",data.get(position).label1)
                intent.putExtra("label2",data.get(position).label2)
                intent.putExtra("tid",data.get(position).tid)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                activity.startActivityForResult(intent,12)
            }
        }
    }

    fun getDoctorData(){
        data.clear()
        place="Doctor"
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("doctor").document("weekdays")
        d1.get().addOnCompleteListener(){
            var day = it.result!!.data?.getValue("title").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var time1 = it.result!!.data?.getValue("timing1").toString().trim()
            var time2 = it.result!!.data?.getValue("timing2").toString().trim()
            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
            notifyDataSetChanged()}
    }
    fun getAdministrationData(){
        data.clear()
        place="Administration"
        db = FirebaseFirestore.getInstance()
        var d1 : DocumentReference = db.collection("Admin").document("weekdays")
        d1.get().addOnCompleteListener(){
            var day = it.result!!.data?.getValue("title").toString().trim()
            var label1 = it.result!!.data?.getValue("label1").toString().trim()
            var label2 = it.result!!.data?.getValue("label2").toString().trim()
            var time1 = it.result!!.data?.getValue("timing1").toString().trim()
            var time2 = it.result!!.data?.getValue("timing2").toString().trim()
            data.add(TimingCardData(title = day, label1 = label1,label2=label2, timing1 = time1, timing2 = time2))
            notifyDataSetChanged()}
    }

    fun getBathroomGirlsTimingData(){
        data.clear()
        place="Bathroom Girls"
        db = FirebaseFirestore.getInstance()
        db.collection("bathroom girls")
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
    }

    fun getBathroomBoysTimingData(){
        data.clear()
        place="Bathroom Boys"
        db = FirebaseFirestore.getInstance()
        db.collection("bathroom")
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
            })}
    
}


