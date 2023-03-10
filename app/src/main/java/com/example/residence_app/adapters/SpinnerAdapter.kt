package com.example.residence_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.residence_app.R
import com.google.protobuf.Internal.ListAdapter

class SpinnerAdapter(var arr:ArrayList<String>,var c:Context) : BaseAdapter(),Filterable {
    override fun getCount(): Int {
        return arr.size
    }

    override fun getItem(position: Int): Any {
       return arr[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       return LayoutInflater.from(c).inflate(R.layout.dropdown_item,parent,false)
    }

    override fun getFilter(): Filter? {
        return null
    }
}