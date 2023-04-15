package com.example.residence_app.data

import android.net.Uri

data class AdminFeedbackData(
    val title:String,
    val first_name:String,
    val last_name:String,
    val img: Uri?,
    val president:String,
    val details:String

)
