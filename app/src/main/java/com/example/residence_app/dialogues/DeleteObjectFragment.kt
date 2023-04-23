package com.example.residence_app.dialogues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.Interfaces.DeleteObjInterface
import com.example.residence_app.R


class DeleteObjectFragment(val inter:DeleteObjInterface) : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_delete_object, container, false)
        val delete= view.findViewById<Button>(R.id.deleteobject)
        val cancel = view.findViewById<ConstraintLayout>(R.id.canceldeleteobject)

        delete.setOnClickListener {
            inter.DeleteObj()
            this.dismiss()
        }
        cancel.setOnClickListener {
            this.dismiss()
        }
        return view
    }


}