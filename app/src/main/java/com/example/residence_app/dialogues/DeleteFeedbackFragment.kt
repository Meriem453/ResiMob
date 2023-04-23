package com.example.residence_app.dialogues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.Interfaces.DeleteFeedbackInterface
import com.example.residence_app.R


class DeleteFeedbackFragment(val inter:DeleteFeedbackInterface) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delete_feedback, container, false)
        val delete=view.findViewById<Button>(R.id.deletefeedback)
        val cancel=view.findViewById<ConstraintLayout>(R.id.canceldeletefeedback)

        cancel.setOnClickListener {
            this.dismiss()
        }
        delete.setOnClickListener {
            inter.DeleteFeedback()
            this.dismiss()
        }

        return view
    }


}