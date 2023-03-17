package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.residence_app.R
import com.example.residence_app.sportDetailsFragment

class SportDaysAdapter(var c:Context,val array: Array<String>,var fm:android.app.FragmentManager) : RecyclerView.Adapter<SportDaysAdapter.DaysVH>() {

    inner class DaysVH(itemView: View):ViewHolder(itemView){
        val day=itemView.findViewById<TextView>(R.id.day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysVH {
        val itemView=LayoutInflater.from(c).inflate(R.layout.day_card,parent,false)
        itemView.setOnClickListener {
            val fragmentTransaction = fm.beginTransaction()
            val fragment=sportDetailsFragment()
            fragment.c=c
            fragmentTransaction.add(R.id.sport_frame,fragment )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return DaysVH(itemView)
    }

    override fun getItemCount(): Int {
       return array.size
    }

    override fun onBindViewHolder(holder: DaysVH, position: Int) {
      holder.day.text=array[position]
    }

}


