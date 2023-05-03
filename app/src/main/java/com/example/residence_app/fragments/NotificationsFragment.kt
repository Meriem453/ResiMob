package com.example.residence_app.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.residence_app.R
import com.example.residence_app.adapters.GymTimingAdapter
import com.example.residence_app.adapters.NotificationsAdapter
import com.example.residence_app.adapters.ObjectsAdapter
import com.example.residence_app.data.NotificationData
import com.example.residence_app.dialogues.ShowNotification
import kotlin.math.log


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [NotificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationsFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.notificationrecycler)
        initRecyclerView(recycler)
        return view
    }
    private fun initRecyclerView(recycler: RecyclerView) {
        val adapter= NotificationsAdapter(requireContext(),false,requireActivity().supportFragmentManager)
        recycler.adapter=adapter
        adapter.getNotifications()
        adapter.setOnItemClickListener(object : NotificationsAdapter.OnItemClickListener{
            override fun onItemClick(item: NotificationData) {
                var t = item.title
                var d = item.details
                var ti = item.time
                var img = check(item.image.toString())

                ShowNotification(title = t!!, details = d!!, image = img!! ).show(requireActivity().supportFragmentManager,"hhh")
            }

        })

        recycler.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        var layoutManager :LinearLayoutManager= recycler.layoutManager as LinearLayoutManager
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
    }
    private fun check(text:String): String {
        if(text == null){
            return ""
        }else{
            return text
        }
    }



}