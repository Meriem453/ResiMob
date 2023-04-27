package com.example.residence_app.adapters
import android.annotation.SuppressLint
import android.app.DownloadManager.Request
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.residence_app.Interfaces.DeleteObjInterface
import com.example.residence_app.R
import com.example.residence_app.data.ObjectData
import com.example.residence_app.dialogues.DeleteObjectFragment
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage

class ObjectsAdapter(var c:Context,val request:Int,val fm:FragmentManager) : RecyclerView.Adapter<ObjectsAdapter.ObjVH>(),DeleteObjInterface {
var arr=ArrayList<ObjectData>()
    lateinit var db : FirebaseFirestore
    lateinit var ds : FirebaseStorage
    var position=0
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
        val delete=itemView.findViewById<ImageView>(R.id.adminobj_delete)


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

                  Glide.with(c).load(arr[position].Img).into(img)

              }
               if(request==1){
                   delete.visibility=View.GONE
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
              delete.setOnClickListener {
                  this@ObjectsAdapter.position=position
                  DeleteObjectFragment(this@ObjectsAdapter).show(fm,"ffff")

              }

          }
    }

    private fun deleteObject(objectData: ObjectData) {

        val oid =objectData.oid.toString()
        val person = objectData.Person.toString()
        ds = FirebaseStorage.getInstance()
        if (person == "Founder"){
            db.collection("found objects").document(oid).delete().addOnSuccessListener{
                ds.reference.child("images/$oid"+"f.jpg").delete().addOnCompleteListener { Toast.makeText(c,"object deleted",
                    Toast.LENGTH_LONG).show()  }
                  }.addOnFailureListener { Toast.makeText(c,"Error!",
                Toast.LENGTH_LONG).show() }
        }else{

            db.collection("lost objects").document(oid).delete().addOnSuccessListener{

                ds.reference.child("images/$oid"+"l.jpg").delete().addOnCompleteListener { Toast.makeText(c,"object deleted",
                    Toast.LENGTH_LONG).show()  }
                 }.addOnFailureListener { Toast.makeText(c,"Error!",
                Toast.LENGTH_LONG).show() }
        }




        getFonderData()
        getLoserData()



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
                            arr.add(dc.getDocument().toObject(ObjectData::class.java))


                        }
                    }
                    notifyDataSetChanged()

                }
            })


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


                        }
                    }
                    notifyDataSetChanged()
                }
            })

    }

    override fun DeleteObj() {
        deleteObject(arr[position])
    }
}