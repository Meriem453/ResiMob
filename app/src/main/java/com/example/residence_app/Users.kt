package com.example.residence_app

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.Interfaces.RefreshAdapter
import com.example.residence_app.adapters.UsersAdapter
import com.google.firebase.firestore.auth.User

class Users : BaseActivity(), RefreshAdapter {
    lateinit var sadapter:ArrayAdapter<String>
    lateinit var search:AutoCompleteTextView
    lateinit var adapter:UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        supportActionBar?.hide()

        search=findViewById<AutoCompleteTextView>(R.id.user_search)
        val rec=findViewById<RecyclerView>(R.id.users_rec)
         val add=findViewById<Button>(R.id.users_add)
        rec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)

         adapter=UsersAdapter(baseContext,this)
        adapter.getUsersData()
        rec.adapter=adapter
        rec.addItemDecoration(DividerItemDecoration(baseContext,LinearLayoutManager.VERTICAL))
       sadapter = ArrayAdapter<String>(baseContext,R.layout.dropdown_item, adapter.search_arr)
        search.setAdapter(sadapter)
        search.setOnItemClickListener(
            AdapterView.OnItemClickListener { parent, view, position, id ->
               for (i in adapter.arr){
                   if("${i.fname} ${i.lname}"==adapter.search_arr[position]){
                       val intent= Intent(baseContext,EditUserActivity::class.java)
                       intent.putExtra("current_user",i)
                       startActivity(intent)
                       break

                   }
               }
            }
        )

add.setOnClickListener {
    startActivity(Intent(baseContext,AddUserActivity::class.java))
}




    }

    override fun refresh() {
        sadapter=ArrayAdapter<String>(baseContext,R.layout.dropdown_item, adapter.search_arr)
        search.setAdapter(sadapter)
    }
}