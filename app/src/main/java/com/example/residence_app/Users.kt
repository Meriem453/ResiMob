package com.example.residence_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.adapters.UsersAdapter
import com.example.residence_app.data.UserInfo
import com.google.firebase.firestore.auth.User

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val search=findViewById<AutoCompleteTextView>(R.id.user_search)
        val rec=findViewById<RecyclerView>(R.id.users_rec)

        rec.layoutManager=LinearLayoutManager(baseContext,RecyclerView.VERTICAL,false)
        val adapter=UsersAdapter(baseContext)
        adapter.getUsersData()
        rec.adapter=adapter
        rec.addItemDecoration(DividerItemDecoration(baseContext,LinearLayoutManager.VERTICAL))

        search.setAdapter(ArrayAdapter<String>(baseContext,R.layout.dropdown_item, adapter.search_arr))
        search.setOnItemClickListener(
            AdapterView.OnItemClickListener { parent, view, position, id ->
               for (i in adapter.arr){
                   if("${i.FirstName} ${i.LastName}"==adapter.search_arr[position]){
                       val intent= Intent(baseContext,EditUserActivity::class.java)
                       intent.putExtra("current_user",i)
                       startActivity(intent)
                       break

                   }
               }
            }
        )





    }
}