package com.example.residence_app.data

import android.net.Uri

data class UserInfo(
    val FirstName:String,
    val LastName:String,
    val Email:String,
    val Password:String,
    val Image:Uri,
    val isAdmin:Boolean
)
