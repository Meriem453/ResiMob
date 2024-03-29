package com.example.residence_app.dialogues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.residence_app.Interfaces.DeleteUserInterface
import com.example.residence_app.R


class DeleteUserFragment(val inter: DeleteUserInterface) : DialogFragment() {

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
            inter.deleteUser()
            this.dismiss()
        }


        return view
    }




}