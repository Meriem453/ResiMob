package com.example.residence_app.data

import android.net.Uri

data class ObjectData(
    val Title:String ?= null,
    val Person:String?=null,
    val Img: Uri??=null,
    val Place:String?=null,
    val Details:String?=null,
    val UserFirstName:String?=null,
    val UserLastName:String?=null,
    val UserEmail:String?=null
                      )
