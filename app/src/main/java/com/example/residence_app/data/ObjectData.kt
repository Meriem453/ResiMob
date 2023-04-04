package com.example.residence_app.data

import android.net.Uri

data class ObjectData(
    val Title:String,
    val Person:String,
    val Img: Uri?,
    val Place:String,
    val Details:String,
    val UserFirstName:String,
    val UserLastName:String,
    val UserEmail:String
                      )
