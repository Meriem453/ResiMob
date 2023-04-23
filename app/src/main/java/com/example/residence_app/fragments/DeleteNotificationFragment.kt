package com.example.residence_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.Interfaces.DeleteNotificationInterface
import com.example.residence_app.R

class DeleteNotificationFragment(val inter:DeleteNotificationInterface) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete_notification, container, false)
        val delete=view.findViewById<Button>(R.id.deletenotification)
        val cancel=view.findViewById<ConstraintLayout>(R.id.canceldeletenotification)

        delete.setOnClickListener {
            inter.DeleteNotification()
            this.dismiss()
        }
        cancel.setOnClickListener {
            this.dismiss()
        }


        return view
    }


}