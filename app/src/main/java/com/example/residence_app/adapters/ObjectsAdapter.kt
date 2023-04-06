package com.example.residence_app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.residence_app.R
import com.example.residence_app.data.ObjectData
import com.example.residence_app.data.UserInfo
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.protobuf.Value
import java.net.URI

class ObjectsAdapter(var c:Context) : RecyclerView.Adapter<ObjectsAdapter.ObjVH>() {
var arr=ArrayList<ObjectData>()
    lateinit var db : FirebaseFirestore
    inner class ObjVH(itemView: View) : ViewHolder(itemView){
        val title=itemView.findViewById<TextView>(R.id.obj_title)
        val img=itemView.findViewById<ImageView>(R.id.obj_img)
        val person=itemView.findViewById<TextView>(R.id.obj_person)
        val details=itemView.findViewById<TextView>(R.id.obj_details)
        val place=itemView.findViewById<TextView>(R.id.obj_place)
        val name=itemView.findViewById<TextView>(R.id.obj_name)
        val email=itemView.findViewById<TextView>(R.id.obj_email)
        val layout=itemView.findViewById<LinearLayout>(R.id.layout)
        val icon=itemView.findViewById<View>(R.id.arrow_gray)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjVH {
        val itemView = LayoutInflater.from(c).inflate(R.layout.objects_card,parent,false)

        return ObjVH(itemView)
    }

    override fun getItemCount(): Int {
       return arr.size
    }

    override fun onBindViewHolder(holder: ObjVH, position: Int) {
          with(holder){
              title.text=arr[position].Title
              person.text=arr[position].Person
              details.text=arr[position].Details
              place.text=arr[position].Place
              name.text="  "+arr[position].UserFirstName + " " + arr[position].UserLastName
              email.text="  "+arr[position].UserEmail
              if(arr[position].Person== c.resources.getString(R.string.loser)){
                  img.visibility=View.GONE
              }else{
                  img.setImageURI(arr[position].Img)
              }

              holder.itemView.setOnClickListener {
                  if(layout.visibility== View.GONE){
                     TransitionManager.beginDelayedTransition(holder.itemView as ViewGroup, AutoTransition())
                      holder.layout.visibility = View.VISIBLE
                      holder.icon.rotation=90f

                  }else{
                      //TransitionManager.beginDelayedTransition(holder.itemView as ViewGroup, AutoTransition())
                      holder.layout.visibility = View.GONE
                      holder.icon.rotation=0f




                  }
              }

          }
    }

    fun getFonderData(){
        db = FirebaseFirestore.getInstance()
        db.collection("found objects")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            //arr.add(dc.getDocument().toObject(ObjectData::class.java))
                            //notifyDataSetChanged()
                        }
                    }

                }



            })
arr.add(ObjectData("Cable","Founder",null,"Salle de lecture","Gray","Zemane","Meriem","m_zemanr@estn.dz"))

        notifyDataSetChanged()


    }

    fun getLoserData(){
        db = FirebaseFirestore.getInstance()
        db.collection("lost objects")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if(error != null){

                        Log.e("Data base error!",error.message.toString())
                        return
                    }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            arr.add(dc.getDocument().toObject(ObjectData::class.java))
                            notifyDataSetChanged()
                        }
                    }

                    }



                        })
                    }

                }



