package com.example.residence_app.dialogues

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.bumptech.glide.Glide
import com.example.residence_app.R
import com.google.android.material.textfield.TextInputEditText

class ShowNotification(val title:String, val details:String, val image:String) : AppCompatDialogFragment() {

    lateinit var textTitle:TextView
    lateinit var textDetails:TextView
    lateinit var imageImage:ImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view= activity?.layoutInflater?.inflate(R.layout.shownotification,null)
        builder.setView(view)
        textDetails = view?.findViewById<TextView>(R.id.shnotdetails)!!
        textTitle = view?.findViewById<TextView>(R.id.shnottitle)!!
        imageImage = view.findViewById<ImageView>(R.id.shnotimage)
        Glide.with(requireActivity()).load(image).into(imageImage)
        textTitle.text = title
        textDetails.text = details

        return builder.create()

    }




}