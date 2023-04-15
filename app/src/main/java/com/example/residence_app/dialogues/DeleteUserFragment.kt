package com.example.residence_app.dialogues

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.DeleteUserInterface
import com.example.residence_app.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


class DeleteUserFragment(val inter:DeleteUserInterface) : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= activity?.layoutInflater?.inflate(R.layout.fragment_delete_user_dialog, null)
       val delete=view?.findViewById<Button>(R.id.deleteuser)
        val cancel = view?.findViewById<ConstraintLayout>(R.id.canceldeleteuser)
        cancel?.setOnClickListener {
            this.dismiss()
        }
        delete?.setOnClickListener {

            this.dismiss()
        }


        return view
    }




}