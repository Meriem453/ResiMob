package com.example.residence_app.data

import android.net.Uri
import android.os.Parcelable

data class UserInfo(
    var FirstName:String,
    var LastName:String,
    var Email:String,
    var Password:String,
    var Image: Uri?,
    var isAdmin:Boolean
):java.io.Serializable
