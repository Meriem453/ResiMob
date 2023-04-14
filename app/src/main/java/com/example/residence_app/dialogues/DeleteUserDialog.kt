package com.example.residence_app.dialogues

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment

import com.example.residence_app.R
import com.example.residence_app.Users
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs


class DeleteUserDialog(val c:Context) : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view= activity?.layoutInflater?.inflate(R.layout.fragment_delete_user_dialog, null)
        val dialog=AlertDialog.Builder(c)
        dialog.setView(view)
        /*dialog.setTitle("Delete user")
        dialog.setMessage("Are you sure you want to delete this user?")
        dialog.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
            //deletUser(user)
            dialog.dismiss()
            startActivity(Intent(c, Users::class.java))
        })
        dialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })*/
        return dialog.create()
    }






}