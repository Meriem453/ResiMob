package com.example.residence_app.adapters

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.data.NotificationData
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context
import android.util.Log
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.residence_app.data.ObjectData
import com.example.residence_app.dialogues.AddFoundObject
import com.example.residence_app.dialogues.ShowNotification
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class NotificationsAdapter(var c:Context) : RecyclerView.Adapter<NotificationsAdapter.VH>() {
    private var data = ArrayList<NotificationData>()

    lateinit var db : FirebaseFirestore
   inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val title = itemView.findViewById<TextView>(R.id.notifications_title)
       val info = itemView.findViewById<TextView>(R.id.notification_Info)
       val time = itemView.findViewById<TextView>(R.id.notification_Time)
       val icon = itemView.findViewById<ImageView>(R.id.notification_Icon)
       val card = itemView.findViewById<LinearLayout>(R.id.notificationcard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView= LayoutInflater.from(c).inflate(R.layout.notification_card,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        val item = data[position]
        holder.itemView.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(holder.itemView.context)
            val dialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.shownotification, null)
            dialogBuilder.setView(dialogView)

            val dialogTitle = dialogView.findViewById<TextView>(R.id.shnottitle)
            val dialogDetails = dialogView.findViewById<TextView>(R.id.shnotdetails)

            dialogTitle.text = item.title
            dialogDetails.text = item.details

            dialogBuilder.setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }

        with(holder){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(item)
            }
            val t = data.get(position).title
            val d = data.get(position).details
            val ti = data.get(position).time

            title.text = t
            info.text = d
            time.text= ti
            card.setOnClickListener {  }
            if (data.get(position).type == "Time change"){
                icon.setImageResource(R.drawable.icon_admin_time)
            }else{
                icon.setImageResource(R.drawable.group_130__1_)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: NotificationData)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun getItemCount(): Int {
        return data.size
    }
    fun getNotifications() {
        db = FirebaseFirestore.getInstance()
        db.collection("notifications")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            data.add(dc.getDocument().toObject(NotificationData::class.java))


                        }
                    }
                    notifyDataSetChanged()

                }
            })

    }

}






