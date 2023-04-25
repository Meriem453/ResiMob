package com.example.residence_app.data

import android.net.Uri
import android.os.Parcelable

data class UserInfo(
    var fname:String?= null,
    var lname:String?= null,
    var email:String?= null,
    var password:String?= null,
    var image: String?= null,
    var uid:String?= null,
    var room:String?= null,
    var isadmin:Boolean?= null
):java.io.Serializable
