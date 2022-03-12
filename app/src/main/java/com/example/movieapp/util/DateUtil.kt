package com.example.movieapp.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{
        fun getReleaseDate(dateString: String?): String? {
            var formattedDate = ""
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val targetFormat = SimpleDateFormat("dd MMM yyyy")
            try {
                if (dateString == null) {
                    return formattedDate
                }
                val date = formatter.parse(dateString)
                formattedDate = targetFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                Log.d("TAG", "getFormattedDate: " + e.message)
            }
            return formattedDate
        }
    }
}